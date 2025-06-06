package net.povstalec.sgjourney.client.models.block_entity;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;

public class MovieChevronModel extends GenericChevronModel
{
	protected static final float MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT = 4F / 16;
	protected static final float MOVIE_OUTER_CHEVRON_OUTER_CUTOFF_HEIGHT = 4.4F / 16; // Why this? Don't ask me, it just works for some reason
	protected static final float MOVIE_OUTER_CHEVRON_X_OFFSET = (OUTER_CHEVRON_TOP_OFFSET / (OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_BOTTOM_HEIGHT)) * MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT;
	protected static final float MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS = ((OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS - OUTER_CHEVRON_SIDE_BOTTOM_THICKNESS) / (OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_BOTTOM_HEIGHT)) * MOVIE_OUTER_CHEVRON_OUTER_CUTOFF_HEIGHT;
	protected static final float MOVIE_OUTER_OUTER_X_OFFSET = -0.5F / 16;
	protected static final float MOVIE_OUTER_OUTER_Y_OFFSET = 2F / 16;
	protected static final float MOVIE_OUTER_OUTER_Y_LENGTH = 4F / 16;
	
	public static void renderMovieChevronFront(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		renderLeftMovieChevron(stack, consumer, source, combinedLight);
		renderLeftMovieOuterChevron(stack, consumer, source, combinedLight);
		renderRightMovieChevron(stack, consumer, source, combinedLight);
		renderRightMovieOuterChevron(stack, consumer, source, combinedLight);
	}
	
	protected static void renderLeftMovieChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Left Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				47F/64, 35F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 36F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				50F/64, 35F/64);
		
		//Left Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(52F - MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, (45F - MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT * 16)/64,
				
				-MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(54F - MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, (45F - MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT * 16)/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 36F/64);
		
		//Left Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, 1, 0,
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 36F/64,
				
				-MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				54F/64, 41F/64,
				
				-MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				55F/64, 41F/64,
				
				-OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				55F/64, 36F/64);
		
		//Left Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				46F/64, 36F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				46F/64, 41F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 41F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 36F/64);
		
		//Left Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				-MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				(52F - MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, 41F/64,
				
				-MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(52F - MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, 40F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(54F - MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, 40F/64,
			
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				(54F - MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, 41F/64);
	}
	
	protected static void renderLeftMovieOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();

		//Left Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				47F/64, 46F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 47F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 47F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				50F/64, 46F/64);
		
		//Left Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				-(OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 47F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 56F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 52F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				50F/64, 47F/64);
		
		//Left Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				46F/64, 56F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				47F/64, 47F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				47F/64, 47F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				STARGATE_RING_OFFSET,
				46F/64, 56F/64);

		//Left Right 1
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 52F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 56F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				STARGATE_RING_OFFSET,
				54F/64, 56F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				54F/64, 52F/64);

		//Left Right 2
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, 1, 0,
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 47F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				53F/64, 52F/64,
				
				-(MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET),
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				54F/64, 52F/64,
				
				-(OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET),
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				54F/64, 47F/64);
	}
	
	protected static void renderRightMovieChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Right Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				63F/64, 35F/64,
				
				OUTER_CHEVRON_TOP_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				63F/64, 36F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				60F/64, 36F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
				STARGATE_RING_OFFSET,
				60F/64, 35F/64);
		
		//Right Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			OUTER_CHEVRON_Z_OFFSET,
			60F/64, 36F/64,
			
			MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			OUTER_CHEVRON_Z_OFFSET,
			(56F + MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, (45F - MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT * 16)/64,
			
			MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			OUTER_CHEVRON_Z_OFFSET,
			(58F + MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, (45F - MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT * 16)/64,
			
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 36F/64);
		
		//Right Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 36F/64,
			
			MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			OUTER_CHEVRON_Z_OFFSET,
			63F/64, 41F/64,
			
			MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			STARGATE_RING_OFFSET,
			64F/64, 41F/64,
			
			OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			STARGATE_RING_OFFSET,
			64F/64, 36F/64);
		
		//Right Left
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, 1, 0,
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			STARGATE_RING_OFFSET,
			55F/64, 36F/64,
			
			MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			STARGATE_RING_OFFSET,
			55F/64, 41F/64,
			
			MOVIE_OUTER_CHEVRON_X_OFFSET,
			-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
			OUTER_CHEVRON_Z_OFFSET,
			56F/64, 41F/64,
			
			OUTER_CHEVRON_TOP_OFFSET,
			OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET,
			OUTER_CHEVRON_Z_OFFSET,
			56F/64, 36F/64);
		
		//Right Bottom
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, -1, 0,
				MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(56F + MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, 40F/64,
				
				MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				(56F + MOVIE_OUTER_CHEVRON_X_OFFSET * 16)/64, 41F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				STARGATE_RING_OFFSET,
				(58F + MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, 41F/64,
			
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT,
				OUTER_CHEVRON_Z_OFFSET,
				(58F + MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS * 16)/64, 40F/64);
	}
	
	protected static void renderRightMovieOuterChevron(PoseStack stack, VertexConsumer consumer, MultiBufferSource source, int combinedLight)
	{
		Matrix4f matrix4 = stack.last().pose();
		Matrix3f matrix3 = stack.last().normal();
		
		//Right Top
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 1, 0,
				OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				60F/64, 47F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				60F/64, 46F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				63F/64, 46F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				63F/64, 47F/64);
		
		//Right Front
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 0, 0, 1,
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 56F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				63F/64, 47F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				60F/64, 47F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 52F/64);
		
		//Right Right
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, 1, -1, 0,
				OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				63F/64, 47F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				64F/64, 56F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				STARGATE_RING_OFFSET,
				64F/64, 56F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + 2 * OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				63F/64, 47F/64);

		//Right Left 1
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, -1, 0,
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 56F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 52F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				56F/64, 52F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET - MOVIE_OUTER_OUTER_Y_LENGTH,
				STARGATE_RING_OFFSET,
				56F/64, 56F/64);

		//Right Left 2
		SGJourneyModel.createQuad(consumer, matrix4, matrix3, combinedLight, -1, 1, 0,
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 52F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				OUTER_CHEVRON_Z_OFFSET,
				57F/64, 47F/64,
				
				OUTER_CHEVRON_TOP_OFFSET + OUTER_CHEVRON_SIDE_TOP_THICKNESS + MOVIE_OUTER_OUTER_X_OFFSET,
				OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_SIDE_HEIGHT + OUTER_CHEVRON_Y_OFFSET - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				56F/64, 47F/64,
				
				MOVIE_OUTER_CHEVRON_BOTTOM_THICKNESS + MOVIE_OUTER_CHEVRON_X_OFFSET + MOVIE_OUTER_OUTER_X_OFFSET,
				-OUTER_CHEVRON_BOTTOM_HEIGHT_CENTER + OUTER_CHEVRON_Y_OFFSET + MOVIE_OUTER_CHEVRON_CUTOFF_HEIGHT - MOVIE_OUTER_OUTER_Y_OFFSET,
				STARGATE_RING_OFFSET,
				56F/64, 52F/64);
	}
}
