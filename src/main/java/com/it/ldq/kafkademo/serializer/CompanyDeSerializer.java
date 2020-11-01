package com.it.ldq.kafkademo.serializer;

import com.it.ldq.kafkademo.entity.Company;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;


/**
 * @Auther: ldq
 * @Date: 2020/3/1
 * @Description:
 * @Version: 1.0
 */
public class CompanyDeSerializer implements Deserializer<Company> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Company deserialize(String s, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            if (data.length<8) {
                throw new SerializationException("Size of data received by IntegerDeserializer is shorter than expected!");
            }
            ByteBuffer buffer = ByteBuffer.wrap(data);
            int cid  = buffer.getInt();
            int nameSize  = buffer.getInt();
            byte[] bytes = new byte[nameSize];
            buffer.get(bytes);
            String cname = new String(bytes, "utf-8");
            return null;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void close() {

    }
}
