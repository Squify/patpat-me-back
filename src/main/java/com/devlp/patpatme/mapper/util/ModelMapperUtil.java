package com.devlp.patpatme.mapper.util;

import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;

import java.util.Objects;
import java.util.Optional;

public class ModelMapperUtil {

    public final static ModelMapper modelMapper = new ModelMapper();

    /**
     * Creates a model mapper with a property to skip.
     *
     * @param sourceType      the type of the source
     * @param destinationType the type of the destination
     * @param objectToMap     the object
     * @param toSkips         the element to skip (BE CAREFUL! -> can't be used for several elements to skip of different type)
     * @return the object mapped on the destination type.
     */
    @SafeVarargs
    public static <S, D, V> D createModelMapper(Class<S> sourceType, Class<D> destinationType, S objectToMap,
                                                DestinationSetter<D, V>... toSkips) {
        if (objectToMap == null) {
            return null;
        }

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<S, D> getTypeMap = modelMapper.getTypeMap(sourceType, destinationType);
        TypeMap<S, D> typeMap = getTypeMap == null ? modelMapper.createTypeMap(sourceType, destinationType) : getTypeMap;


        Optional.ofNullable(toSkips).ifPresent(skips -> addMapping(skips, typeMap));

        return modelMapper.map(objectToMap, destinationType);
    }

    private static <D, S, V> void addMapping(DestinationSetter<D, V>[] skips, TypeMap<S, D> typeMap) {
        Lists.newArrayList(skips)
                .stream()
                .filter(Objects::nonNull)
                .forEach(toSkip -> typeMap.addMappings(mapper -> mapper.skip(toSkip)));
    }

}
