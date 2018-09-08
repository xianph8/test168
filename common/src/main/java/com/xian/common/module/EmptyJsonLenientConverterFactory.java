package com.xian.common.module;

import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * https://blog.piasy.com/2016/09/04/RESTful-Android-Network-Solution-2/
 */
public class EmptyJsonLenientConverterFactory extends Converter.Factory {

    private final GsonConverterFactory mGsonConverterFactory;       // 1

    public EmptyJsonLenientConverterFactory(GsonConverterFactory gsonConverterFactory) {
        mGsonConverterFactory = gsonConverterFactory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegateConverter =        // 3
                mGsonConverterFactory.responseBodyConverter(type,
                        annotations, retrofit);
        return new Converter<ResponseBody, Object>() {

            @Override
            public Object convert(ResponseBody value) throws IOException {
                try {
                    return delegateConverter.convert(value);            // 4
                } catch (EOFException e) {
                    // just return null
                    return null;                                        // 5
                }
            }
        };
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return mGsonConverterFactory.requestBodyConverter(type,     // 2
                parameterAnnotations, methodAnnotations, retrofit);
    }
}