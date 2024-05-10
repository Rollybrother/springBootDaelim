package com.mysite.sbb.Daelim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "db_test")
@Data
@NoArgsConstructor @AllArgsConstructor
public class DaelimVO {
	@Id
    @Column(name = "title")
	private String title;
	
	@Column(name = "name")
	private String name;
	
}
