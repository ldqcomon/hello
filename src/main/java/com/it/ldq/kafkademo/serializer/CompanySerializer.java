package com.it.ldq.kafkademo.serializer;

import com.it.ldq.kafkademo.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @Auther: ldq
 * @Date: 2020/3/1
 * @Description:
 * @Version: 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Company company) {
        try {
            byte[] serializeName;
            int stringSize;
            if (company == null) {
                return null;
            }
            else {
                if (company.getCname()!=null) {
                        serializeName = company.getCname().getBytes("utf-8");
                        stringSize = serializeName.length;
                }
                else {
                    serializeName = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(company.getCid());
            buffer.putInt(stringSize);
            buffer.put(serializeName);
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
