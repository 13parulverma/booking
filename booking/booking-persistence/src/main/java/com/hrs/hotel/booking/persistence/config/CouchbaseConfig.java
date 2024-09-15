package com.hrs.hotel.booking.persistence.config;

import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.Collections;
/**
 * Configuration class for Couchbase.
 * This class extends {@link AbstractCouchbaseConfiguration} to provide custom configurations for Couchbase.
 */
@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.hrs.hotel.booking.persistence.repository"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${app.couchbase.connection-string}")
    private String connectionString;

    @Value("${app.couchbase.user-name}")
    private String userName;

    @Value("${app.couchbase.password}")
    private String password;

    @Value("${app.couchbase.bucket-primary}")
    private String bookingBucket;

    @Value("${app.couchbase.bucket-user}")
    private String userBucket;

    @Value("${app.couchbase.bucket-hotel}")
    private String hotelBucket;

    @Autowired
    private ApplicationContext context;

    /**
     * Returns the Couchbase connection string.
     *
     * @return the connection string
     */
    @Override
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Returns the Couchbase username.
     *
     * @return the username
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the Couchbase password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the name of the primary bucket.
     *
     * @return the primary bucket name
     */
    @Override
    public String getBucketName() {
        return bookingBucket;
    }

    /**
     * Configures repository operations mapping for different entities.
     *
     * @param mapping the repository operations mapping
     */
    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        mapping.mapEntity(HotelDTO.class, getCouchbaseTemplate(hotelBucket));
        mapping.mapEntity(UserDTO.class, getCouchbaseTemplate(userBucket));
    }

    /**
     * Creates a {@link CouchbaseTemplate} for the specified bucket name.
     *
     * @param bucketName the name of the bucket
     * @return the Couchbase template
     * @throws Exception if an error occurs while creating the template
     */
    @SneakyThrows
    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) {
        CouchbaseTemplate couchbaseTemplate = new CouchbaseTemplate(
                couchbaseClientFactory(bucketName),
                mappingCouchbaseConverter(couchbaseMappingContext(customConversions()), new CouchbaseCustomConversions(Collections.emptyList()))
        );
        couchbaseTemplate.setApplicationContext(context);
        return couchbaseTemplate;
    }

    /**
     * Creates a {@link CouchbaseClientFactory} for the specified bucket name.
     *
     * @param bucketName the name of the bucket
     * @return the Couchbase client factory
     */
    private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
        return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketName, this.getScopeName());
    }
}