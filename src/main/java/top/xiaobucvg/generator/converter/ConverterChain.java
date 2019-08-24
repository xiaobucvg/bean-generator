package top.xiaobucvg.generator.converter;

import java.util.ArrayList;
import java.util.List;

/***
 * 所有转换器的管理类
 *
 * by Mr.Zhang
 */
public class ConverterChain implements IConverter {

    private List<IConverter> converterList = new ArrayList<>();

    private int index = 0;

    public ConverterChain addConverter(IConverter converter){
        converterList.add(converter);
        return this;
    }

    @Override
    public String convert(String source) {
        if(index == converterList.size()) return null;
        IConverter converter = converterList.get(index);
        index ++;
        return converter.convert(source);
    }

    public void resetIndex(){
        this.index = 0;
    }
}
