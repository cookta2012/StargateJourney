package net.povstalec.sgjourney.common.misc;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.common.config.CommonGenerationConfig;
import net.povstalec.sgjourney.common.init.TagInit;
import net.povstalec.sgjourney.common.structures.BuriedStargate;

public class TreasureMapForEmeraldsTrade implements VillagerTrades.ItemListing
{
	protected final int emeraldCost;
	protected final TagKey<Structure> destination;
	protected final String displayName;
	protected final MapDecoration.Type destinationType;
	protected final int maxUses;
	protected final int villagerXp;
	
	public TreasureMapForEmeraldsTrade(int emeraldCost, TagKey<Structure> destination, String displayName, MapDecoration.Type destinationType, int maxUses, int villagerXp)
	{
		this.emeraldCost = emeraldCost;
		this.destination = destination;
		this.displayName = displayName;
		this.destinationType = destinationType;
		this.maxUses = maxUses;
		this.villagerXp = villagerXp;
	}
	
	@Nullable
	public MerchantOffer getOffer(Entity entity, RandomSource source)
	{
		if(entity.getLevel() instanceof ServerLevel level)
		{
			BlockPos blockpos = level.findNearestMapStructure(this.destination, entity.blockPosition(), 100, true);
			
			if(blockpos != null)
			{
				ItemStack itemstack = MapItem.create(level, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
				MapItem.renderBiomePreviewMap(level, itemstack);
				MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
				itemstack.setHoverName(Component.translatable(this.displayName));
				
				return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
			}
			else
				return null;
		}
		else
			return null;
	}
	
	public static class StargateMapTrade extends TreasureMapForEmeraldsTrade
	{
		public StargateMapTrade(int emeraldCost, String displayName, int villagerXp)
		{
			super(emeraldCost, TagInit.Structures.STARGATE_MAP, displayName, MapDecoration.Type.RED_X, 1, villagerXp);
		}
		
		@Nullable
		public MerchantOffer getOffer(Entity entity, RandomSource source)
		{
			if(entity.getLevel() instanceof ServerLevel level)
			{
				int xOffset = 16 * CommonGenerationConfig.stargate_generation_center_x_chunk_offset.get();
		        int zOffset = 16 * CommonGenerationConfig.stargate_generation_center_z_chunk_offset.get();

				StargateJourney.LOGGER.info("Attempting to locate Buried Stargate for map");
				BlockPos blockpos = level.findNearestMapStructure(this.destination, new BlockPos(xOffset, 0, zOffset), 150, true);
				
				if(blockpos == null && level.dimension().equals(Level.OVERWORLD))
				{
					StargateJourney.LOGGER.info("Couldn't locate Buried Stargate, falling back to BlockPos saved in BuriedStargate");
					blockpos = new BlockPos(16 * BuriedStargate.getX(level.getSeed()), 0, 16 * BuriedStargate.getZ(level.getSeed()));
				}
				
				if(blockpos != null)
				{
					ItemStack itemstack = MapItem.create(level, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
					MapItem.renderBiomePreviewMap(level, itemstack);
					MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
					itemstack.setHoverName(Component.translatable(this.displayName));
					
					return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
				}
			}
			
			return null;
		}
	}
 }
