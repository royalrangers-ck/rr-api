package com.royalrangers.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

@Configuration
public class JacksonAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return  new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false)
                .serializationInclusion(Include.NON_EMPTY)
                .serializerByType(Page.class, new JsonPageSerializer());
    }

    public class JsonPageSerializer extends JsonSerializer<Page> {

        @Override
        public void serialize(Page page, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            ObjectMapper om=new ObjectMapper()
                    .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                    .setSerializationInclusion(Include.NON_EMPTY);
            gen.writeStartObject();
            gen.writeFieldName("size");
            gen.writeNumber(page.getSize());
            gen.writeFieldName("number");
            gen.writeNumber(page.getNumber());
            gen.writeFieldName("totalElements");
            gen.writeNumber(page.getTotalElements());
            gen.writeFieldName("last");
            gen.writeBoolean(page.isLast());
            gen.writeFieldName("totalPages");
            gen.writeNumber(page.getTotalPages());
            gen.writeObjectField("sort", page.getSort());
            gen.writeFieldName("first");
            gen.writeBoolean(page.isFirst());
            gen.writeFieldName("numberOfElements");
            gen.writeNumber(page.getNumberOfElements());
            gen.writeFieldName("content");
            gen.writeRawValue(om.writerWithView(serializers.getActiveView())
                    .writeValueAsString(page.getContent()));
            gen.writeEndObject();
        }
    }
}
