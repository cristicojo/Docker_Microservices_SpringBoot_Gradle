package employees.importJsonTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.runtime.Network;


public class ImportJsonFile {


	@Test
	public void importJsonFileTest() throws IOException {

		Net net = new Net(Network.getFreeServerPort(), Network.localhostIsIPv6());
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(net).build();

		IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder().defaults(Command.MongoD).build();

		MongodExecutable mongodExe = MongodStarter.getInstance(runtimeConfig).prepare(mongodConfig);
		MongodProcess mongod = mongodExe.start();

		File jsonFile = new File(Thread.currentThread().getContextClassLoader().getResource("Untitled.json").getFile());

		String importDatabase = "local";
		String importCollection = "employees2";
		int port = 27017;

		//import json file in the db
		MongoImportExecutable mongoImportExecutable = mongoImportExecutable(port, importDatabase, importCollection, jsonFile.getAbsolutePath(), true, true, true);

		String a = net.getServerAddress().getHostName();

		MongoClient mongoClient = MongoClients.create("mongodb://" + a + ":27017");
		MongoImportProcess mongoImportProcess = mongoImportExecutable.start();


		assertEquals(10, mongoClient.getDatabase(importDatabase).getCollection(importCollection).countDocuments());

		mongoImportProcess.stop();
		mongod.stop();
		mongodExe.stop();


	}


	public MongoImportExecutable mongoImportExecutable(int port, String dbName, String collection, String jsonFile, Boolean jsonArray, Boolean upsert, Boolean drop) throws IOException {

		IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6()))
				.db(dbName)
				.collection(collection)
				.upsert(upsert)
				.dropCollection(drop)
				.jsonArray(jsonArray)
				.importFile(jsonFile)
				.build();

		return MongoImportStarter.getDefaultInstance().prepare(mongoImportConfig);
	}


}

