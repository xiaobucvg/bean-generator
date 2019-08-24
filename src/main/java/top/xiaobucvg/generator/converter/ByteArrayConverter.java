package top.xiaobucvg.generator.converter;

/***
 * 字节数组转换器
 *
 * by Mr.Zhang
 */
public class ByteArrayConverter implements IConverter{

    private ConverterChain converterChain;

    public ByteArrayConverter(ConverterChain converterChain) {
        this.converterChain = converterChain;
    }

    @Override
    public String convert(String source) {
        if("[B".equals(source)){
            return "byte[]";
        }
        return this.converterChain.convert(source);
    }
}
