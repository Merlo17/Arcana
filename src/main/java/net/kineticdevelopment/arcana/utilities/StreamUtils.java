package net.kineticdevelopment.arcana.utilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class StreamUtils{
	
	public static <X extends NBTBase, Z> Stream<Z> streamAndApply(NBTTagList list, Class<X> filterType, Function<X, Z> applicator){
		return StreamSupport.stream(list.spliterator(), false)
				.filter(filterType::isInstance)
				.map(filterType::cast)
				.map(applicator);
	}
	
	public static <T> Supplier<T> cached(Supplier<T> creator){
		T cached = creator.get();
		return () -> cached;
	}
}