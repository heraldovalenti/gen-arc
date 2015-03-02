package com.genarc.services.data;

import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.genarc.dal.dao.AbstractDao;
import com.genarc.model.BaseEntity;
import com.genarc.model.entities.Tag;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jettison.json.JSONObject;

@Path("/data")
@Stateless(mappedName = "ejb/dataService", name = "dataService")
public class DataService {

	static final String PERSISTENCE_UNIT = "Entities";
	static final String ENTITIES_PACKAGE = "com.genarc.model.entities";
	static final String DAOS_PACKAGE = "com.genarc.dal.dao";
	static final String DAO_SUFFIX = "Dao";

	@PersistenceContext(unitName = PERSISTENCE_UNIT)
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@POST
	@Path("/save/{entityName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@PathParam("entityName") String entityName)
			throws Exception {
		Class<?> entityClass = Class.forName(ENTITIES_PACKAGE + "."
				+ entityName);
		Class<?> daoClass = Class.forName(DAOS_PACKAGE + "." + entityName
				+ DAO_SUFFIX);
		// ObjectMapper mapper = new ObjectMapper();
		// BaseEntity entity = (BaseEntity) mapper.readValue(json.toString(),
		// entityClass);
		BaseEntity entity = (BaseEntity) entityClass.newInstance();
		AbstractDao<BaseEntity> dao = (AbstractDao<BaseEntity>) daoClass
				.newInstance();
		dao.setEntityManager(entityManager);
		entity = dao.create(entity);
		return Response.ok(entity, MediaType.APPLICATION_JSON).build();
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/savetag/{entityName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveTag(@PathParam("entityName") String entityName,
			Map<String,String> map) throws Exception {
		Class<?> entityClass = Class.forName(ENTITIES_PACKAGE + "."	+ entityName);
		Class<?> daoClass = Class.forName(DAOS_PACKAGE + "." + entityName + DAO_SUFFIX);
		
		AbstractDao<BaseEntity> dao = (AbstractDao<BaseEntity>) daoClass.newInstance();
		dao.setEntityManager(entityManager);
		Tag entity = (Tag) entityClass.newInstance();
		entity.setName(map.get("name"));
		entity = (Tag) dao.create(entity);
		return Response.ok(entity, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/sayHello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response helloWorld() {
		return Response.ok("Hello world!", MediaType.TEXT_PLAIN).build();
	}

}
