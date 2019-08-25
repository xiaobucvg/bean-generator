package top.xiaobucvg.generator;

import top.xiaobucvg.generator.util.FieldUtil;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * bean 生成器，根据数据表自动生成 javabean 文件
 */
public class BeanGenerator {

    private static StringBuilder stringBuilder = new StringBuilder();

    // javaBean 的包信息，需指定
    private  String packageInfo;

    // 生成的的bean所在的目录，需指定
    private  File targetDir;

    // 是否实现序列化接口，默认不实现
    private  boolean isSerializable;

    public BeanGenerator(String packageInfo, String targetDir, boolean isSerializable) {
        this.packageInfo = packageInfo;
        this.targetDir = new File(targetDir);
        this.isSerializable = isSerializable;
    }

    /***
     * 生成包信息
     */
    private  void createPackageInfo() {
        stringBuilder.append("package ").append(packageInfo).append(";\n\n");
    }

    /***
     * 生成Get和Set
     */
    private  void createGetAndSet(BeanDescription beanDes) {
        Map<String, String> mapper = beanDes.getFieldMapper();
        mapper.forEach((name, type) -> {
            String newName = FieldUtil.firstWordToUpper(name);
            stringBuilder.append("\t").append("public void set").append(newName).append("(){\n\n\t}\n\n");
            stringBuilder.append("\t").append("public ").append(type).append(" ").append("get").append(newName).append("(){\n").append("\t\treturn this.").append(name).append(";\n").append("\t}\n\n");
        });
    }

    /***
     * 生成
     */
    public  void create(List<BeanDescription> beanDescriptions) {
        beanDescriptions.forEach(this::create);
    }

    /***
     * 根据一个Bean描述创建一个Bean
     */
    public  void create(BeanDescription beanDes) {
        // package
        createPackageInfo();
        // import
        createImport(beanDes);
        // 类名
        String beanName = beanDes.getName();
        stringBuilder.append("public class ").append(beanName);
        if (isSerializable) {
            stringBuilder.append(" implements Serializable");
        }
        stringBuilder.append("{\n\n");
        Map<String, String> mapper = beanDes.getFieldMapper();
        mapper.forEach((fieldName, type) -> stringBuilder.append("\t").append("private ").append(type).append(" ").append(fieldName).append(";\n\n"));
        // getAndSet
        createGetAndSet(beanDes);
        stringBuilder.append("}");
        // 写入文件
        write(beanName);
        // 清空内容
        stringBuilder = new StringBuilder();
    }

    /***
     * 创建 import
     */
    private  void createImport(BeanDescription beanDes) {
        if (isSerializable) {
            stringBuilder.append("import java.io.Serializable;\n");
        }
        Set<String> importClasess = beanDes.getImportClasess();
        if (importClasess != null) {
            importClasess.forEach(clazz -> {
                stringBuilder.append("import ").append(clazz).append(";\n");
            });
        }
        stringBuilder.append("\n");
    }

    /***
     * 写入当前目录
     */
    private void write(String javaName) {
        File file = new File(this.targetDir.getAbsoluteFile() + "/" + javaName + ".java");
        if(!targetDir.exists()){
            targetDir.mkdirs();
        }
        FileChannel channel = null;
        try {
            file.createNewFile();
            channel = FileChannel.open(file.toPath(), StandardOpenOption.WRITE);
            ByteBuffer buffer = Charset.forName("UTF-8").encode(stringBuilder.toString());
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("生成了文件：" + javaName + ".java");
    }

}


