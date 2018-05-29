package vk.vadim.karavayev.converter;

public interface Converter<F, T> {
    T convert(F from);
}
