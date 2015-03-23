package com.genarc.services;

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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

import com.genarc.dal.dao.AbstractDao;
import com.genarc.model.BaseEntity;
import com.genarc.model.entities.Tag;

//import org.codehaus.jackson.map.ObjectMapper;

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
	@Path("/savetest/{entityName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveTag(
			@PathParam("entityName") String entityName,
			JSONObject json) throws Exception {
		Class<?> entityClass = Class.forName(ENTITIES_PACKAGE + "."	+ entityName);
		Class<?> daoClass = Class.forName(DAOS_PACKAGE + "." + entityName + DAO_SUFFIX);
		
		JAXBContext jc = JAXBContext.newInstance(entityClass);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Configuration config = new Configuration();
	    MappedNamespaceConvention con = new MappedNamespaceConvention(config);
	    XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(json, con);
		
	    AbstractDao<Tag> dao = (AbstractDao<Tag>) daoClass.newInstance();
		dao.setEntityManager(entityManager);
		
		Tag entity = (Tag) unmarshaller.unmarshal(xmlStreamReader);
		entity = (Tag) dao.create(entity);
		
		return Response.ok(entity, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/sayHello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		Tag t = new Tag();
		t.setId(123L);
		t.setName("Home");
		return Response.ok(t, MediaType.APPLICATION_JSON).build();
	}
	
	public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Tag.class);
 
        //JSONObject obj = new JSONObject("{\"customer\":{\"id\":123,\"first-name\":\"Jane\",\"last-name\":\"Doe\",\"address\":{\"street\":\"123 A Street\"},\"phone-number\":[{\"@type\":\"work\",\"$\":\"555-1111\"},{\"@type\":\"cell\",\"$\":\"555-2222\"}]}}");
        JSONObject obj = new JSONObject("{ 'tag' : { 'id' : 123, 'name' : 'Home', 'att' : 345 } }");
        //JSONObject obj = new JSONObject("{ 'id' : 123, 'name' : 'Home' }");
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);
 
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Tag t = (Tag) unmarshaller.unmarshal(xmlStreamReader);
 
        System.out.println(t.getId());
        System.out.println(t.getName());
    }

}
