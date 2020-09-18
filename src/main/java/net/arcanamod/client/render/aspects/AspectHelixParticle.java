package net.arcanamod.client.render.aspects;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.particle.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AspectHelixParticle extends SpriteTexturedParticle{
	
	private float time;
	private final IAnimatedSprite spriteSheet;
	private final Vec3d direction;
	
	protected AspectHelixParticle(World world, double x, double y, double z, IAnimatedSprite spriteSheet, AspectHelixParticleData data){
		super(world, x, y, z);
		this.spriteSheet = spriteSheet;
		selectSpriteWithAge(spriteSheet);
		maxAge = data.getLife();
		time = data.getTime();
		direction = data.getDirection();
		canCollide = false;
		motionX = direction.x * .05;
		motionY = direction.y * .05;
		motionZ = direction.z * .05;
		if(data.getAspect() != null){
			int colour = data.getAspect().getColorRange().get(0);
			int r = (colour & 0xff0000) >> 16;
			int g = (colour & 0xff00) >> 8;
			int b = colour & 0xff;
			particleRed = Math.min(r / 127f, 1);
			particleGreen = Math.min(g / 127f, 1);
			particleBlue = Math.min(b / 127f, 1);
		}
		setSize(0.02F, 0.02F);
		particleScale *= rand.nextFloat() * 0.3f + 0.7f;
	}
	
	public void tick(){
		selectSpriteWithAge(spriteSheet);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if(age++ >= maxAge)
			setExpired();
		else{
			float f = .6f;
			float x1 = f * MathHelper.cos(time);
			float z1 = f * MathHelper.sin(time);
			
			// There are certain directions that appear mostly straight due to this.
			// I can't figure this out.
			Vec3d cross1 = new Vec3d(-1, -1, -1).normalize();
			Vec3d cross2 = new Vec3d(1, -1, -1).normalize();
			Vec3d cross = cross1.dotProduct(direction) < cross2.dotProduct(direction) ? cross1 : cross2;
			
			Vec3d y = direction.mul(.05, .05, .05);
			Vec3d z = cross.crossProduct(direction);
			Vec3d x = z.crossProduct(direction);
			z = z.mul(z1, z1, z1).mul(.07, .07, .07);
			x = x.mul(x1, x1, x1).mul(.07, .07, .07);
			
			motionX = x.x + y.x + z.x;
			motionY = x.y + y.y + z.y;
			motionZ = x.z + y.z + z.z;
			move(motionX, motionY, motionZ);
			
			time += .2f;
		}
	}
	
	public IParticleRenderType getRenderType(){
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	
	public static class Factory implements IParticleFactory<AspectHelixParticleData>{
		private final IAnimatedSprite spriteSet;
		
		public Factory(IAnimatedSprite sheet){
			this.spriteSet = sheet;
		}
		
		public Particle makeParticle(AspectHelixParticleData data, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed){
			return new AspectHelixParticle(world, x, y, z, spriteSet, data);
		}
	}
}