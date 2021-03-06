/**
 * 
 */
package org.vsg.cusp.plugins.apps;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author Vison Ruan
 *
 */
public abstract class AbstractAppMojo extends AbstractMojo {
	
	@Parameter( defaultValue = "${project}", readonly = true, required = true )
	private MavenProject mavenProject;
	
	
	public MavenProject getProject() {
		return mavenProject;
	}


	

	
	

}
