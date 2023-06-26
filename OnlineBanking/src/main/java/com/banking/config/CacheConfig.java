package com.banking.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.List;

import org.springframework.cache.annotation.EnableCaching;
import com.banking.model.TransactionEntity;

@Configuration
@EnableCaching
public class CacheConfig {
	

	@Bean
	 CacheManager ehCacheManager() {
	    CachingProvider provider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
	    CacheManager cacheManager = provider.getCacheManager();

	    CacheConfiguration<Integer, List> configuration =
	            CacheConfigurationBuilder.newCacheConfigurationBuilder(
	            		Integer.class,
	            		List.class,
	                    ResourcePoolsBuilder.newResourcePoolsBuilder()
	                            .heap(1000, EntryUnit.ENTRIES) // In-memory heap size
	                            .build())
	                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))
	                    .build();

	    cacheManager.createCache("listOfTransaction",
	            Eh107Configuration.fromEhcacheCacheConfiguration(configuration));

	    return cacheManager;
	}

	@Bean
	public org.springframework.cache.CacheManager cacheManager() {
	    JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(ehCacheManager());
	    return jCacheCacheManager;
	}

}