package net.kineticdevelopment.arcana.common.init;

import java.util.ArrayList;
import java.util.List;

import net.kineticdevelopment.arcana.common.objects.blocks.TaintedBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block TAINTED_CRUST = new TaintedBlockBase("tainted_crust", Material.ROCK);
	public static final Block TAINTED_GRAVEL = new TaintedBlockBase("tainted_gravel", Material.GROUND);
	public static final Block TAINTED_ROCK = new TaintedBlockBase("tainted_rock", Material.ROCK);
}
