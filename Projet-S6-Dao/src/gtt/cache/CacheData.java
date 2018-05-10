package gtt.cache;

import org.cacheonix.Cacheonix;
import org.cacheonix.cache.Cache;

import gtt.model.BaseModel;
import gtt.model.setting.Contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.*;

public class CacheData {
	public CacheData() {}
	
	public void initCache(Duration duration, boolean enabled) {
		Cacheonix cacheManager = Cacheonix.getInstance();
		
		Cache<String, Duration> durationCache = cacheManager.getCache("duration.cache");
		String durationKey = "duration";
        Duration durationValue = duration;
        durationCache.put(durationKey, durationValue);
        
        Cache<String, Boolean> enabledCache = cacheManager.getCache("enabled.cache");
        String enabledKey = "enabled";
        Boolean enabledValue = enabled;
        enabledCache.put(enabledKey, enabledValue);
	}
	
	public Duration getDuration() {
		Duration ret = null;
		Cacheonix cacheManager = Cacheonix.getInstance();
		if(cacheManager.cacheExists("duration.cache")) {
			Cache<String, Duration> cache = cacheManager.getCache("duration.cache");
        	ret = cache.get("duration");
		}
		return ret;
	}
	
	public LocalDateTime getLastUpdateDate() {
		LocalDateTime ret = null;
		Cacheonix cacheManager = Cacheonix.getInstance();
		if(cacheManager.cacheExists("lastUpdateDate.cache")) {
			Cache<String, LocalDateTime> cache = cacheManager.getCache("lastUpdateDate.cache");
			ret = cache.get("lastUpdateDate");
		}
		return ret;
	}
	
	public void setLastUpdateDate() {
		Cacheonix cacheManager = Cacheonix.getInstance();
		Cache<String, LocalDateTime> cache = cacheManager.getCache("lastUpdateDate.cache");
		String enabledKey = "lastUpdateDate";
		LocalDateTime enabledValue = LocalDateTime.now();
        cache.put(enabledKey, enabledValue);
	}
	
	public boolean isEnabled() {
		Boolean ret = false;
		Cacheonix cacheManager = Cacheonix.getInstance();
		if(cacheManager.cacheExists("enabled.cache")) {
			Cache<String, Boolean> cache = cacheManager.getCache("enabled.cache");
			ret = cache.get("enabled");
		}
		return ret;
	}
	
	public boolean isExpired() {
		Duration currentDuration = Duration.between(this.getLastUpdateDate(), LocalDateTime.now());
		return currentDuration.compareTo(this.getDuration())>0;
	}
	
	@SuppressWarnings("rawtypes")
	private Object[] convertListToArray(List arrayModel) {
		Object[] result = new Contest[arrayModel.size()];
		int i = 0;
		for(Object single : arrayModel) {
			result[i] = single;
			i++;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public void putInCache(List listModel, String cacheName) throws Exception{
		System.out.println("updating cache...");
		Object[] modelContest = this.convertListToArray(listModel);
		Cacheonix cacheManager = Cacheonix.getInstance();
		Cache<String, Object[]> dataCache = cacheManager.getCache(cacheName + ".cache");
		String contestKey = cacheName;
        Object[] contestValue = modelContest;
        dataCache.put(contestKey, contestValue);
        this.setLastUpdateDate();
	}
	
	@SuppressWarnings("rawtypes")
	public List findFromCache(BaseModel model, String cacheName) throws Exception {
		List result = new ArrayList<>();
		Cacheonix cacheManager = Cacheonix.getInstance();
		if(cacheManager.cacheExists(cacheName + ".cache")) {
			System.out.println("reading cache");
			Cache<String, Object[]> cache = cacheManager.getCache(cacheName + ".cache");
        	Object[] arrayModel = cache.get(cacheName);
        	
    		result = Arrays.asList(arrayModel);
    	}else {
			throw new Exception("The cache " + cacheName + " doesn't exist.");
		}
	
		return result;
	}
	
}
