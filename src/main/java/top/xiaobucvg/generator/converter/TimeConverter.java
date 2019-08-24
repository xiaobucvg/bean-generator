package top.xiaobucvg.generator.converter;

/***
 * 时间类型的转化器
 */
public class TimeConverter implements IConverter {

    private ConverterChain converterChain;

    public TimeConverter(ConverterChain converterChain) {
        this.converterChain = converterChain;
    }

    @Override
    public String convert(String source) {
        if(source.equals("java.sql.Date") || source.equals("java.sql.Time") || source.equals("java.sql.Timestamp")){
            return "java.util.Date";
        }
        return this.converterChain.convert(source);
    }
}
