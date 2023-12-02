package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final CommonsLibraryAccessors laccForCommonsLibraryAccessors = new CommonsLibraryAccessors(owner);
    private final GroovyLibraryAccessors laccForGroovyLibraryAccessors = new GroovyLibraryAccessors(owner);
    private final HutoolLibraryAccessors laccForHutoolLibraryAccessors = new HutoolLibraryAccessors(owner);
    private final JacksonLibraryAccessors laccForJacksonLibraryAccessors = new JacksonLibraryAccessors(owner);
    private final MybatisLibraryAccessors laccForMybatisLibraryAccessors = new MybatisLibraryAccessors(owner);
    private final MysqlLibraryAccessors laccForMysqlLibraryAccessors = new MysqlLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for freemarker (org.freemarker:freemarker)
         * This dependency was declared in settings file 'settings.gradle.kts'
         */
        public Provider<MinimalExternalModuleDependency> getFreemarker() {
            return create("freemarker");
    }

        /**
         * Creates a dependency provider for lombok (org.projectlombok:lombok)
         * This dependency was declared in settings file 'settings.gradle.kts'
         */
        public Provider<MinimalExternalModuleDependency> getLombok() {
            return create("lombok");
    }

    /**
     * Returns the group of libraries at commons
     */
    public CommonsLibraryAccessors getCommons() {
        return laccForCommonsLibraryAccessors;
    }

    /**
     * Returns the group of libraries at groovy
     */
    public GroovyLibraryAccessors getGroovy() {
        return laccForGroovyLibraryAccessors;
    }

    /**
     * Returns the group of libraries at hutool
     */
    public HutoolLibraryAccessors getHutool() {
        return laccForHutoolLibraryAccessors;
    }

    /**
     * Returns the group of libraries at jackson
     */
    public JacksonLibraryAccessors getJackson() {
        return laccForJacksonLibraryAccessors;
    }

    /**
     * Returns the group of libraries at mybatis
     */
    public MybatisLibraryAccessors getMybatis() {
        return laccForMybatisLibraryAccessors;
    }

    /**
     * Returns the group of libraries at mysql
     */
    public MysqlLibraryAccessors getMysql() {
        return laccForMysqlLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class CommonsLibraryAccessors extends SubDependencyFactory {

        public CommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for codec (commons-codec:commons-codec)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getCodec() {
                return create("commons.codec");
        }

            /**
             * Creates a dependency provider for collections4 (org.apache.commons:commons-collections4)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getCollections4() {
                return create("commons.collections4");
        }

            /**
             * Creates a dependency provider for io (commons-io:commons-io)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getIo() {
                return create("commons.io");
        }

            /**
             * Creates a dependency provider for lang3 (org.apache.commons:commons-lang3)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getLang3() {
                return create("commons.lang3");
        }

    }

    public static class GroovyLibraryAccessors extends SubDependencyFactory {

        public GroovyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.codehaus.groovy:groovy)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("groovy.core");
        }

            /**
             * Creates a dependency provider for json (org.codehaus.groovy:groovy-json)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getJson() {
                return create("groovy.json");
        }

            /**
             * Creates a dependency provider for nio (org.codehaus.groovy:groovy-nio)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getNio() {
                return create("groovy.nio");
        }

    }

    public static class HutoolLibraryAccessors extends SubDependencyFactory {

        public HutoolLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for all (cn.hutool:hutool-all)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getAll() {
                return create("hutool.all");
        }

    }

    public static class JacksonLibraryAccessors extends SubDependencyFactory {
        private final JacksonDataformatLibraryAccessors laccForJacksonDataformatLibraryAccessors = new JacksonDataformatLibraryAccessors(owner);

        public JacksonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotations (com.fasterxml.jackson.core:jackson-annotations)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() {
                return create("jackson.annotations");
        }

            /**
             * Creates a dependency provider for core (com.fasterxml.jackson.core:jackson-core)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("jackson.core");
        }

            /**
             * Creates a dependency provider for databind (com.fasterxml.jackson.core:jackson-databind)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getDatabind() {
                return create("jackson.databind");
        }

        /**
         * Returns the group of libraries at jackson.dataformat
         */
        public JacksonDataformatLibraryAccessors getDataformat() {
            return laccForJacksonDataformatLibraryAccessors;
        }

    }

    public static class JacksonDataformatLibraryAccessors extends SubDependencyFactory {

        public JacksonDataformatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for yaml (com.fasterxml.jackson.dataformat:jackson-dataformat-yaml)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getYaml() {
                return create("jackson.dataformat.yaml");
        }

    }

    public static class MybatisLibraryAccessors extends SubDependencyFactory {
        private final MybatisPlusLibraryAccessors laccForMybatisPlusLibraryAccessors = new MybatisPlusLibraryAccessors(owner);
        private final MybatisSpringLibraryAccessors laccForMybatisSpringLibraryAccessors = new MybatisSpringLibraryAccessors(owner);

        public MybatisLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mybatis.plus
         */
        public MybatisPlusLibraryAccessors getPlus() {
            return laccForMybatisPlusLibraryAccessors;
        }

        /**
         * Returns the group of libraries at mybatis.spring
         */
        public MybatisSpringLibraryAccessors getSpring() {
            return laccForMybatisSpringLibraryAccessors;
        }

    }

    public static class MybatisPlusLibraryAccessors extends SubDependencyFactory {
        private final MybatisPlusBootLibraryAccessors laccForMybatisPlusBootLibraryAccessors = new MybatisPlusBootLibraryAccessors(owner);

        public MybatisPlusLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for generator (com.baomidou:mybatis-plus-generator)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getGenerator() {
                return create("mybatis.plus.generator");
        }

        /**
         * Returns the group of libraries at mybatis.plus.boot
         */
        public MybatisPlusBootLibraryAccessors getBoot() {
            return laccForMybatisPlusBootLibraryAccessors;
        }

    }

    public static class MybatisPlusBootLibraryAccessors extends SubDependencyFactory {

        public MybatisPlusBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for starter (com.baomidou:mybatis-plus-boot-starter)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getStarter() {
                return create("mybatis.plus.boot.starter");
        }

    }

    public static class MybatisSpringLibraryAccessors extends SubDependencyFactory {
        private final MybatisSpringBootLibraryAccessors laccForMybatisSpringBootLibraryAccessors = new MybatisSpringBootLibraryAccessors(owner);

        public MybatisSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mybatis.spring.boot
         */
        public MybatisSpringBootLibraryAccessors getBoot() {
            return laccForMybatisSpringBootLibraryAccessors;
        }

    }

    public static class MybatisSpringBootLibraryAccessors extends SubDependencyFactory {

        public MybatisSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for starter (org.mybatis.spring.boot:mybatis-spring-boot-starter)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getStarter() {
                return create("mybatis.spring.boot.starter");
        }

    }

    public static class MysqlLibraryAccessors extends SubDependencyFactory {
        private final MysqlConnectorLibraryAccessors laccForMysqlConnectorLibraryAccessors = new MysqlConnectorLibraryAccessors(owner);

        public MysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mysql.connector
         */
        public MysqlConnectorLibraryAccessors getConnector() {
            return laccForMysqlConnectorLibraryAccessors;
        }

    }

    public static class MysqlConnectorLibraryAccessors extends SubDependencyFactory {

        public MysqlConnectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (mysql:mysql-connector-java)
             * This dependency was declared in settings file 'settings.gradle.kts'
             */
            public Provider<MinimalExternalModuleDependency> getJava() {
                return create("mysql.connector.java");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: springBootVersion (3.1.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle.kts'
             */
            public Provider<String> getSpringBootVersion() { return getVersion("springBootVersion"); }

            /**
             * Returns the version associated to this alias: springCloudVersion (4.0.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle.kts'
             */
            public Provider<String> getSpringCloudVersion() { return getVersion("springCloudVersion"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for jackson which is an aggregate for the following dependencies:
             * <ul>
             *    <li>com.fasterxml.jackson.core:jackson-core</li>
             *    <li>com.fasterxml.jackson.core:jackson-databind</li>
             *    <li>com.fasterxml.jackson.core:jackson-annotations</li>
             *    <li>com.fasterxml.jackson.dataformat:jackson-dataformat-yaml</li>
             * </ul>
             * This bundle was declared in settings file 'settings.gradle.kts'
             */
            public Provider<ExternalModuleDependencyBundle> getJackson() {
                return createBundle("jackson");
            }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
