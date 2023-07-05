package com.myshop.myshop.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import com.alibaba.fastjson.parser.ParserConfig;
public class redisConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${slave.redis.port}")
    private String slavePort;
    @Value("${redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${redis.jedis.pool.max-wait}")
    private int maxWait;
    @Bean("jedisConnectionSystemConfigFactory")
    LettuceConnectionFactory jedisConnectionSystemConfigFactory(){
        return getMSLettuceConnectionFactory(redisHost,redisPort,6);
    }
    @Bean
    @Qualifier(value = "redisSystemConfigTemplate")
    RedisTemplate<String,Object> redisSystemConfigTemplate(){
        return getRedisTemplate(jedisConnectionSystemConfigFactory());
    }
    private LettuceConnectionFactory getMSLettuceConnectionFactory (String host, int redisPort, int redisDataBase) {
        LettucePoolingClientConfiguration configuration = LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig())
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();
        RedisStaticMasterReplicaConfiguration masterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(host, redisPort);
        masterReplicaConfiguration.setDatabase(redisDataBase);
        masterReplicaConfiguration.setPassword(redisPassword);
//        Arrays.stream(slavePort.split(",")).forEach(port -> masterReplicaConfiguration.addNode(host,Integer.parseInt(port)));
        return new LettuceConnectionFactory(masterReplicaConfiguration, configuration);
    }

    private RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //序列畫
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);

        ParserConfig.getGlobalInstance().addAccept("com.icchance.*");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        return redisTemplate;
    }
    private GenericObjectPoolConfig genericObjectPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }
}