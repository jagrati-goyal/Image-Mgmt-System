package com.nagarro.itemmanagement.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Image.class)
public abstract class Image_ {

	public static volatile SingularAttribute<Image, String> path;
	public static volatile SingularAttribute<Image, byte[]> image;
	public static volatile SingularAttribute<Image, Integer> imageId;
	public static volatile SingularAttribute<Image, String> imageName;
	public static volatile SingularAttribute<Image, Long> imageSize;

	public static final String PATH = "path";
	public static final String IMAGE = "image";
	public static final String IMAGE_ID = "imageId";
	public static final String IMAGE_NAME = "imageName";
	public static final String IMAGE_SIZE = "imageSize";

}

