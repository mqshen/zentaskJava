package org.goldratio.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** 
 * ClassName: BaseModel <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 3, 2013 8:57:40 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@MappedSuperclass
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class BaseModel {

	@Id
	@GeneratedValue(generator="sequenceStyleGenerator")
	@GenericGenerator(name="sequenceStyleGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
					  parameters={@Parameter(name=SequenceStyleGenerator.SEQUENCE_PARAM, value="ZENTASK_ID_SEQUENCE"),
								  @Parameter(name=SequenceStyleGenerator.INITIAL_PARAM, value="2000"),
							      @Parameter(name=SequenceStyleGenerator.INCREMENT_PARAM, value="1"), 
								  @Parameter(name=SequenceStyleGenerator.OPT_PARAM, value="pooled")})
	private Long id;
	
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of the entity.
	 * 
	 * @param id the id to set
	 */
	protected void setId(final Long id) {
		this.id = id;
	}
}
