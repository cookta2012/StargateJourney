package net.povstalec.sgjourney.client.models.block_entity;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;

public class GenericChevronModel
{
	protected static final float STARGATE_RING_OFFSET = GenericStargateModel.STARGATE_RING_OFFSET;
	
	protected static final float CHEVRON_LIGHT_FRONT_LENGTH = 4F / 16;
	protected static final float CHEVRON_LIGHT_DIVIDER_LENGTH = 1F / 16;
	protected static final float CHEVRON_LIGHT_BACK_LENGTH = 4F / 16;

	protected static final float CHEVRON_LIGHT_LENGTH = CHEVRON_LIGHT_FRONT_LENGTH + CHEVRON_LIGHT_DIVIDER_LENGTH + CHEVRON_LIGHT_BACK_LENGTH;

	protected static final float CHEVRON_DIVIDER_HEIGHT = 1F / 16;
	
	protected static final float CHEVRON_LIGHT_Z_OFFSET = CHEVRON_LIGHT_DIVIDER_LENGTH / 2;
	protected static final float CHEVRON_LIGHT_FRONT_Z_OFFSET = CHEVRON_LIGHT_Z_OFFSET + CHEVRON_LIGHT_FRONT_LENGTH;
	protected static final float CHEVRON_LIGHT_BACK_Z_OFFSET = -CHEVRON_LIGHT_Z_OFFSET - CHEVRON_LIGHT_BACK_LENGTH;
	
	protected static final float CHEVRON_LIGHT_TOP_WIDTH = 6F / 16;
	protected static final float CHEVRON_LIGHT_TOP_CENTER = CHEVRON_LIGHT_TOP_WIDTH / 2;
	protected static final float CHEVRON_LIGHT_BOTTOM_WIDTH = 3F / 16;
	protected static final float CHEVRON_LIGHT_BOTTOM_CENTER = CHEVRON_LIGHT_BOTTOM_WIDTH / 2;
	protected static final float CHEVRON_LIGHT_HEIGHT = 7F / 16;
	protected static final float CHEVRON_LIGHT_HEIGHT_CENTER = CHEVRON_LIGHT_HEIGHT / 2;
	
	protected static final float CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER = CHEVRON_LIGHT_BOTTOM_CENTER + (CHEVRON_LIGHT_TOP_CENTER - CHEVRON_LIGHT_BOTTOM_CENTER) * ((CHEVRON_LIGHT_HEIGHT - CHEVRON_DIVIDER_HEIGHT) / CHEVRON_LIGHT_HEIGHT);
	protected static final float CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT = CHEVRON_LIGHT_HEIGHT_CENTER - CHEVRON_DIVIDER_HEIGHT;
	
	protected static final float OUTER_CHEVRON_Z_THICKNESS = 1F / 16;
	protected static final float OUTER_CHEVRON_Z_OFFSET = OUTER_CHEVRON_Z_THICKNESS + STARGATE_RING_OFFSET;

	protected static final float OUTER_CHEVRON_SIDE_HEIGHT = 7F / 16;
	protected static final float OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS = 2F / 16;
	protected static final float OUTER_CHEVRON_SIDE_TOP_THICKNESS = OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS + 1F / 16;
	protected static final float OUTER_CHEVRON_TOP_OFFSET = 4F / 16;
	
	protected static final float OUTER_CHEVRON_LOWER_BOTTOM_WIDTH = 4F / 16;
	protected static final float OUTER_CHEVRON_LOWER_BOTTOM_CENTER = OUTER_CHEVRON_LOWER_BOTTOM_WIDTH / 2;
	
	protected static final float OUTER_CHEVRON_BOTTOM_HEIGHT = 2F / 16;
	protected static final float OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER = OUTER_CHEVRON_BOTTOM_HEIGHT / 2;
	protected static final float OUTER_CHEVRON_Y_OFFSET = -6.5F / 16;
	
	protected static final float OUTER_CHEVRON_UPPER_BOTTOM_CENTER = (OUTER_CHEVRON_TOP_OFFSET / (OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_BOTTOM_HEIGHT)) * OUTER_CHEVRON_BOTTOM_HEIGHT;
	
	public static void renderChevronLight(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isFrontRaised, boolean isBackRaised)
	{
		renderChevronLightFront(stack, consumer, source, combinedLight, isFrontRaised);
		renderChevronLightCenter(stack, consumer, source, combinedLight, isFrontRaised, isBackRaised);
		renderChevronLightBack(stack, consumer, source, combinedLight, isBackRaised);
	}
	
	protected static void renderChevronLightFront(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isRaised)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		float yOffset = isRaised ? 2F / 16 : 0;
		//Light Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				48F/64, 17F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				48F/64, 13F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				54F/64, 13F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				54F/64, 17F/64);
		
		//Light Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				48F/64, 17F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				49.5F/64, 24F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				52.5F/64, 24F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				54F/64, 17F/64);

		//Light Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				48F/64, 24F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				48F/64, 17F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				44F/64, 17F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				44F/64, 24F/64);

		//Light Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				54F/64, 17F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				54F/64, 24F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				58F/64, 24F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				58F/64, 17F/64);

		//Light Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				54.5F/64, 13F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				54.5F/64, 17F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_Z_OFFSET,
				57.5F/64, 17F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_FRONT_Z_OFFSET,
				57.5F/64, 13F/64);
		
		if(isRaised)
		{
			//Light Back
			SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
					CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					CHEVRON_LIGHT_Z_OFFSET,
					58F/64, 17F/64,
					
					CHEVRON_LIGHT_BOTTOM_CENTER,
					-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					CHEVRON_LIGHT_Z_OFFSET,
					59.5F/64, 24F/64,
					
					-CHEVRON_LIGHT_BOTTOM_CENTER,
					-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					CHEVRON_LIGHT_Z_OFFSET,
					62.5F/64, 24F/64,
					
					-CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					CHEVRON_LIGHT_Z_OFFSET,
					64F/64, 17F/64);
		}
	}
	
	protected static void renderChevronLightCenter(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isFrontRaised, boolean isBackRaised)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Light Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				CHEVRON_LIGHT_Z_OFFSET,
				51F/64, 11F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				-CHEVRON_LIGHT_Z_OFFSET,
				51F/64, 12F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				-CHEVRON_LIGHT_Z_OFFSET,
				57F/64, 12F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				CHEVRON_LIGHT_Z_OFFSET,
				57F/64, 11F/64);
		
		if(isFrontRaised)
		{
			//Light Front
			SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
					-CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER,
					CHEVRON_LIGHT_Z_OFFSET,
					51F/64, 12F/64,
					
					-CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
					CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
					CHEVRON_LIGHT_Z_OFFSET,
					(51F + 1.5F * ((CHEVRON_LIGHT_HEIGHT - CHEVRON_DIVIDER_HEIGHT) / CHEVRON_LIGHT_HEIGHT))/64, 13F/64,
					
					CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
					CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
					CHEVRON_LIGHT_Z_OFFSET,
					(57F - 1.5F * ((CHEVRON_LIGHT_HEIGHT - CHEVRON_DIVIDER_HEIGHT) / CHEVRON_LIGHT_HEIGHT))/64, 13F/64,
					
					CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER,
					CHEVRON_LIGHT_Z_OFFSET,
					57F/64, 12F/64);
		}

		//Light Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
				CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
				CHEVRON_LIGHT_Z_OFFSET,
				51F/64, 13F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				CHEVRON_LIGHT_Z_OFFSET,
				51F/64, 12F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				-CHEVRON_LIGHT_Z_OFFSET,
				50F/64, 12F/64,
				
				-CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
				CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
				-CHEVRON_LIGHT_Z_OFFSET,
				50F/64, 13F/64);

		//Light Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				CHEVRON_LIGHT_Z_OFFSET,
				57F/64, 12F/64,
				
				CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
				CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
				CHEVRON_LIGHT_Z_OFFSET,
				57F/64, 13F/64,
				
				CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
				CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
				-CHEVRON_LIGHT_Z_OFFSET,
				58F/64, 13F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER,
				-CHEVRON_LIGHT_Z_OFFSET,
				58F/64, 12F/64);
		
		if(isBackRaised)
		{
			//Light Back
			SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
					CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER,
					-CHEVRON_LIGHT_Z_OFFSET,
					58F/64, 12F/64,
					
					CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
					CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
					-CHEVRON_LIGHT_Z_OFFSET,
					(58F + 1.5F * ((CHEVRON_LIGHT_HEIGHT - CHEVRON_DIVIDER_HEIGHT) / CHEVRON_LIGHT_HEIGHT))/64, 13F/64,
					
					-CHEVRON_LIGHT_DIVIDER_BOTTOM_CENTER,
					CHEVRON_LIGHT_DIVIDER_BOTTOM_HEIGHT,
					-CHEVRON_LIGHT_Z_OFFSET,
					(64F - 1.5F * ((CHEVRON_LIGHT_HEIGHT - CHEVRON_DIVIDER_HEIGHT) / CHEVRON_LIGHT_HEIGHT))/64, 13F/64,
					
					-CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER,
					-CHEVRON_LIGHT_Z_OFFSET,
					64F/64, 12F/64);
		}
	}
	
	protected static void renderChevronLightBack(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isRaised)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();

		float yOffset = isRaised ? 2F / 16 : 0;
		//Light Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				48F/64, 4F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				48F/64, 0F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				54F/64, 0F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				54F/64, 4F/64);
		
		if(isRaised)
		{
			//Light Front
			SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
					-CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					-CHEVRON_LIGHT_Z_OFFSET,
					47F/64, 4F/64,
					
					-CHEVRON_LIGHT_BOTTOM_CENTER,
					-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					-CHEVRON_LIGHT_Z_OFFSET,
					48.5F/64, 11F/64,
					
					CHEVRON_LIGHT_BOTTOM_CENTER,
					-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					-CHEVRON_LIGHT_Z_OFFSET,
					51.5F/64, 11F/64,
					
					CHEVRON_LIGHT_TOP_CENTER,
					CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
					-CHEVRON_LIGHT_Z_OFFSET,
					53F/64, 4F/64);
		}

		//Light Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				48F/64, 11F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				48F/64, 4F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				44F/64, 4F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				44F/64, 11F/64);

		//Light Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				54F/64, 4F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				54F/64, 11F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				58F/64, 11F/64,
				
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				58F/64, 4F/64);

		//Light Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				54.5F/64, 0F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				54.5F/64, 4F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				57.5F/64, 4F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				-CHEVRON_LIGHT_Z_OFFSET,
				57.5F/64, 0F/64);
		
		//Light Back
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
				CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				58F/64, 4F/64,
				
				CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				59.5F/64, 11F/64,
				
				-CHEVRON_LIGHT_BOTTOM_CENTER,
				-CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				62.5F/64, 11F/64,
				
				-CHEVRON_LIGHT_TOP_CENTER,
				CHEVRON_LIGHT_HEIGHT_CENTER + yOffset,
				CHEVRON_LIGHT_BACK_Z_OFFSET,
				64F/64, 4F/64);
	}
	
	public static void renderOuterChevronFront(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isOpen)
	{
		float yOffset = isOpen ? -2F / 16 : 0;
		renderCenterOuterChevron(stack, consumer, source, combinedLight, isOpen, yOffset);
		renderLeftOuterChevron(stack, consumer, source, combinedLight, isOpen, yOffset);
		renderRightOuterChevron(stack, consumer, source, combinedLight, isOpen, yOffset);
	}
	
	protected static void renderCenterOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isOpen, float yOffset)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();

		//Center Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				54F/64, 43F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 44F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				56F/64, 44F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				56F/64, 43F/64);
		
		//Center Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				(55F - OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS * 16)/64, 43F/64,
				
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				55F/64, 45F/64,
				
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				55F/64, 45F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				(55F + OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS * 16)/64, 43F/64);
		
		//Center Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 45F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				53F/64, 46F/64,
				
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				57F/64, 46F/64,
				
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 45F/64);
	}
	
	protected static void renderLeftOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isOpen, float yOffset)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Left Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				47F/64, 35F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 36F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				50F/64, 35F/64);
		
		//Left Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				52F/64, 45F/64,
				
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 45F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 36F/64);
		
		//Left Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, 1, 0,
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 36F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 43F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				55F/64, 43F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				55F/64, 36F/64);
		
		//Left Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				46F/64, 36F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				46F/64, 45F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 45F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64);
	}
	
	protected static void renderRightOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight, boolean isOpen, float yOffset)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Right Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				60F/64, 35F/64,
				
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				60F/64, 36F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				OUTER_CHEVRON_Z_OFFSET,
				63F/64, 36F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
				STARGATE_RING_OFFSET,
				63F/64, 35F/64);
		
		//Right Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			60F/64, 36F/64,
			
			0F / 16,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			56F/64, 45F/64,
			
			OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			58F/64, 45F/64,
			
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 36F/64);
		
		//Right Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 36F/64,
			
			OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 45F/64,
			
			OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			STARGATE_RING_OFFSET,
			64F/64, 45F/64,
			
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			STARGATE_RING_OFFSET,
			64F/64, 36F/64);
		
		//Right Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, 1, 0,
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			STARGATE_RING_OFFSET,
			55F/64, 36F/64,
			
			OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			STARGATE_RING_OFFSET,
			55F/64, 43F/64,
			
			OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			56F/64, 43F/64,
			
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET + yOffset,
			OUTER_CHEVRON_Z_OFFSET,
			56F/64, 36F/64);
	}
	
	public static void renderOuterChevronBack(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		renderBackCenterOuterChevron(stack, consumer, source, combinedLight);
		renderLeftOuterChevron(stack, consumer, source, combinedLight);
		renderRightOuterChevron(stack, consumer, source, combinedLight);
	}
	
	protected static void renderBackCenterOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();

		//Center Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				54F/64, 32F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				54F/64, 33F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				56F/64, 33F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				56F/64, 32F/64);
		
		//Center Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				55F/64, 34F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				(55F - OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS * 16)/64, 32F/64,
				
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				(55F + OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS * 16)/64, 32F/64,
				
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				55F/64, 34F/64);
		
		//Center Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				53F/64, 34F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				53F/64, 35F/64,
				
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				57F/64, 35F/64,
				
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				57F/64, 34F/64);
	}
	
	protected static void renderLeftOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Left Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				47F/64, 24F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				47F/64, 25F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				50F/64, 25F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				50F/64, 24F/64);
		
		//Left Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				52F/64, 34F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				47F/64, 25F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				50F/64, 25F/64,
				
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				54F/64, 34F/64);
		
		//Left Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, 0, 0,
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				54F/64, 32F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				54F/64, 25F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				55F/64, 25F/64,
				
				-OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				55F/64, 32F/64);
		
		//Left Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, 0, 0,
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				46F/64, 34F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				46F/64, 25F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				47F/64, 25F/64,
				
				-OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				47F/64, 34F/64);
	}
	
	protected static void renderRightOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Right Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				60F/64, 24F/64,
				
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				60F/64, 25F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				63F/64, 25F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				63F/64, 24F/64);
		
		//Right Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, -1,
				0F / 16,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				56F/64, 34F/64,
				
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				60F/64, 25F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				63F/64, 25F/64,
				
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				58F/64, 34F/64);
		
		//Right Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, 0, 0,
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				63F/64, 34F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				63F/64, 25F/64,
			
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				64F/64, 25F/64,
			
				OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				64F/64, 34F/64);
		
		//Right Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, 0, 0,
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				55F/64, 32F/64,
				
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-STARGATE_RING_OFFSET,
				55F/64, 25F/64,
			
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				56F/64, 25F/64,
			
				OUTER_CHEVRON_UPPER_BOTTOM_CENTER,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET,
				-OUTER_CHEVRON_Z_OFFSET,
				56F/64, 32F/64);
	}
}
