package ludlows.github.io.config;

import org.apache.ibatis.session.SqlSessionFactory;
import ludlows.github.io.common.DataSourceEnum;
import ludlows.github.io.common.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@MapperScan(basePackages = "ludlows.github.io.mapper")
public class MyBatisConfig {

    /**
     * @return
     * @throws Exception
     * @Primary This comment indicates which implementation class to choose by default when multiple implementation classes can be injected into the same interface, rather than error-reporting the @autowire comment
     */
    @Primary
    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource masterDataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    @Bean("slaverDataSource")
    @ConfigurationProperties(prefix = "datasource.slaver")
    public DataSource slaverDataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    /**
     * @Qualifier Injection by name, usually one of several instances of the same type (for example, multiple instances of DataSource type)
     */
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaverDataSource") DataSource slaverDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.master, masterDataSource);
        targetDataSources.put(DataSourceEnum.slaver, slaverDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// This method is AbstractRoutingDataSource's method
        dataSource.setDefaultTargetDataSource(masterDataSource);// The default datasource setting is myTestDbDataSource

        return dataSource;
    }

    /**
     * Create SqlSessionFactory from Data Source
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource,
                                               @Value("mybatis.typeAliasesPackage") String typeAliasesPackage,
                                               @Value("mybatis.mapperLocations") String mapperLocations) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        // Specify the data source (this must exist, otherwise an error will occur)
        // The next two sentences are for *.xml files only,
        // if the XML file is not needed for the entire persistence layer operation (only annotations will do), they are not added
        //factoryBean.setTypeAliasesPackage(typeAliasesPackage);
        // Specify base package
        //factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//

        return factoryBean.getObject();
    }

    /**
     * Configure Transaction Manager
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}