package com.db.javaschool.finalproject.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.db.javaschool.finalproject.JsonViews;
import com.db.javaschool.finalproject.dao.newsentry.ProductDao;
import com.db.javaschool.finalproject.entity.Product;

@Component
@Path("/products")
public class ProductResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {
		this.logger.info("list()");

		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		List<Product> allEntries = this.productDao.findAll();

		return viewWriter.writeValueAsString(allEntries);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id_product}")
	public Product read(@PathParam("id_product") Long id_product) {
		this.logger.info("read(id_product)");

		Product product = this.productDao.find(id_product);
		if (product == null) {
			throw new WebApplicationException(404);
		}
		return product;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Product create(Product newsEntry) {
		this.logger.info("create(): " + newsEntry);

		return this.productDao.save(newsEntry);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id_product}")
	public Product update(@PathParam("id_product") Long id_product, Product product) {
		this.logger.info("update(): " + product);

		return this.productDao.save(product);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id_product}")
	public void delete(@PathParam("id_product") Long id_product) {
		this.logger.info("delete(id_product)");

		this.productDao.delete(id_product);
	}

	private boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}

}