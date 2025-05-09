package net.povstalec.sgjourney.common.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.povstalec.sgjourney.StargateJourney;

public class StatisticsInit
{
	public static final DeferredRegister<ResourceLocation> STATISTICS = DeferredRegister.create(Registries.CUSTOM_STAT, StargateJourney.MODID);
	private static final List<Runnable> STATISTIC_SETUP = new ArrayList<>();
	
	public static final DeferredHolder<ResourceLocation, ResourceLocation> TIMES_USED_WORMHOLE = STATISTICS.register("times_used_wormhole", () -> registerDefaultStatistic("times_used_wormhole"));
	public static final DeferredHolder<ResourceLocation, ResourceLocation> TIMES_KILLED_BY_KAWOOSH = STATISTICS.register("times_killed_by_kawoosh", () -> registerDefaultStatistic("times_killed_by_kawoosh"));
	public static final DeferredHolder<ResourceLocation, ResourceLocation> TIMES_KILLED_BY_WORMHOLE = STATISTICS.register("times_killed_by_wormhole", () -> registerDefaultStatistic("times_killed_by_wormhole"));
	public static final DeferredHolder<ResourceLocation, ResourceLocation> TIMES_SMASHED_AGAINST_IRIS = STATISTICS.register("times_smashed_against_iris", () -> registerDefaultStatistic("times_smashed_against_iris"));
	public static final DeferredHolder<ResourceLocation, ResourceLocation> DISTANCE_TRAVELED_BY_STARGATE = STATISTICS.register("distance_traveled_by_stargate", () -> registerDistanceStatistic("distance_traveled_by_stargate"));
	
	
	private static ResourceLocation registerDefaultStatistic(String key)
	{
		ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(StargateJourney.MODID, key);
		STATISTIC_SETUP.add(() -> Stats.CUSTOM.get(resourceLocation, StatFormatter.DEFAULT));
		return resourceLocation;
	}
	
	private static ResourceLocation registerDistanceStatistic(String key)
	{
		ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(StargateJourney.MODID, key);
		STATISTIC_SETUP.add(() -> Stats.CUSTOM.get(resourceLocation, StatFormatter.DISTANCE));
		return resourceLocation;
	}

	public static void register()
	{
		STATISTIC_SETUP.forEach(Runnable::run);
	}

	public static void register(IEventBus bus)
	{
		STATISTICS.register(bus);
	}
}

