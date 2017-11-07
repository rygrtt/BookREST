package com.bookrest.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// creates a new annotation for secured resources
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {}