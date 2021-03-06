package net.arcanamod.systems.spell.modules.circle;

import net.arcanamod.aspects.Aspect;
import net.arcanamod.aspects.AspectUtils;
import net.arcanamod.systems.spell.modules.CircleSpellModule;
import net.minecraft.nbt.CompoundNBT;

public class DoubleModifierCircle extends CircleSpellModule{
	public Aspect firstAspect;
	public Aspect secondAspect;

	@Override
	public String getName() {
		return "double_modifier_circle";
	}

	@Override
	public CompoundNBT toNBT(){
		CompoundNBT compound = new CompoundNBT();
		compound.putString("firstAspect", firstAspect.toResourceLocation().toString());
		compound.putString("secondAspect", secondAspect.toResourceLocation().toString());
		return compound;
	}

}
