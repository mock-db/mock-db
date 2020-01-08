package com.silensoft.micromock.mockdb;

import com.google.inject.AbstractModule;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReader;
import com.silensoft.micromock.mockdb.configfile.ConfigFileReaderImpl;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandlerFactory;
import com.silensoft.micromock.mockdb.databasehandler.DatabaseHandlerFactoryImpl;
import com.silensoft.micromock.mockdb.expectations.ExpectationsParser;
import com.silensoft.micromock.mockdb.expectations.ExpectationsParserImpl;
import com.silensoft.micromock.mockdb.expectations.ExpectationsStore;
import com.silensoft.micromock.mockdb.expectations.ExpectationsStoreImpl;
import com.silensoft.micromock.mockdb.mocks.MocksParser;
import com.silensoft.micromock.mockdb.mocks.MocksParserImpl;
import com.silensoft.micromock.mockdb.mocks.MocksStore;
import com.silensoft.micromock.mockdb.mocks.MocksStoreImpl;

public class MockDbModule extends AbstractModule {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void configure() {
        bind(ConfigFileReader.class).to(ConfigFileReaderImpl.class).asEagerSingleton();
        bind(MocksParser.class).to(MocksParserImpl.class).asEagerSingleton();
        bind(DatabaseHandlerFactory.class).to(DatabaseHandlerFactoryImpl.class).asEagerSingleton();
        bind(ExpectationsParser.class).to(ExpectationsParserImpl.class).asEagerSingleton();
        bind(MocksStore.class).to(MocksStoreImpl.class).asEagerSingleton();
        bind(ExpectationsStore.class).to(ExpectationsStoreImpl.class).asEagerSingleton();
    }
}
