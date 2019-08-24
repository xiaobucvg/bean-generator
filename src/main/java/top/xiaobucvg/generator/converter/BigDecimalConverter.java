package top.xiaobucvg.generator.converter;

/***
 * 大数字转换器,实际上没有转换,只是为了获得 import
 *
 * by Mr.Zhang
 */
public class BigDecimalConverter implements IConverter {

    private ConverterChain converterChain;

    public BigDecimalConverter(ConverterChain converterChain) {
        this.converterChain = converterChain;
    }

    @Override
    public String convert(String source) {
        if("java.math.BigDecimal".equals(source)){
            return "java.math.BigDecimal";
        }
        return this.converterChain.convert(source);
    }
}
