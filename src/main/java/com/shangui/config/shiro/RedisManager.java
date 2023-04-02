package com.shangui.config.shiro;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.WorkAloneRedisManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisManager extends WorkAloneRedisManager implements IRedisManager {
    private static final String DEFAULT_HOST = "127.0.0.1:6379";
    private String host = "127.0.0.1";
    private String port = "6379";
    private int timeout = 2000;
    private String password;
    private int database = 0;
    private JedisPool jedisPool;

    public RedisManager() {
    }

    private void init() {
        synchronized(this) {
            if (this.jedisPool == null) {
//                String[] hostAndPort = this.host.split(":");
                this.jedisPool = new JedisPool(this.getJedisPoolConfig(), this.host, Integer.parseInt(this.port),
                        this.timeout, this.password, this.database);
            }

        }
    }

    protected Jedis getJedis() {
        if (this.jedisPool == null) {
            this.init();
        }

        return this.jedisPool.getResource();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
