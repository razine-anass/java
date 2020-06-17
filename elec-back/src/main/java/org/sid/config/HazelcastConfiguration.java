package org.sid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

/**                 un fournisseur de cache permet un paramétrage avancé du cache de l'application
 * 
	 * En utilisant un fournisseur de cache , on peut définir plusieurs caches avec différents paramètres d'une manière très simple :
	
	name : un identifiant du cache ;
	maxBytesLocalHeap : définit le nombre d'octets que le cache peut utiliser de la VM. Si un CacheManager maxBytesLocalHeap a été défini, la taille déterminée de ce cache sera soustraite du CacheManager. D'autres caches partagent le reste. Les valeurs de cet attribut sont données sous la forme <nombre> k | K | M | M | g |G (k | K pour kilo-octets, m | M pour mégaoctets, ou g | G pour gigaoctets). Par exemple : 30m pour 30 mégaoctets ;
	eternal : définit si les éléments sont éternels. Si c'est le cas, le timeout sera ignoré et l'élément n'est jamais expiré ;
	timeToIdleSeconds : c'est le nombre de secondes que l'élément doit vivre depuis sa dernière utilisation. La valeur par défaut est 0, l'élément reste toujours en repos ;
	timeToLiveSeconds : c'est le nombre de secondes que l'élément doit vivre depuis sa création en cache. La valeur par défaut est 0, l'élément vivra éternellement ;
	memoryStoreEvictionPolicy : politique d'éviction :
	
	LRU - le moins récemment utilisé,
	LFU - moins fréquemment utilisé,
	FIFO - premier entré, premier sorti, l'élément le plus ancien par date de création ;
	diskExpiryThreadIntervalSeconds : nombre de secondes entre deux exécutions du processus de contrôle d'éviction ;
	diskPersistent : permet la mémorisation des objets sur le disque pour une récupération des objets entre deux démarrages de la VM ;
	overflowToDisk : détermine si les objets peuvent être stockés sur le disque en cas d'atteinte du maximum d'éléments en mémoire.
	 *
 */

@Configuration
public class HazelcastConfiguration {

 @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("user")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(60));
    }

}