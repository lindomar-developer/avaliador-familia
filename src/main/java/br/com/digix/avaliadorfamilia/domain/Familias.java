package br.com.digix.avaliadorfamilia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FAMILIA")
public class Familias {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "familia_gen")
    @SequenceGenerator(name = "familia_gen", sequenceName = "FAMILIA_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "RENDA_TOTAL")
    private BigDecimal rendaTotal;

    @ToString.Exclude
    @OneToMany(mappedBy = "familias" ,fetch = FetchType.LAZY)
    private List<Dependentes> dependentes;

    public void adicionaDependente(Dependentes dependentes) {
        if(ObjectUtils.isEmpty(this.dependentes)){
            this.dependentes = new ArrayList<>();
        }

        if ( !ObjectUtils.isEmpty(dependentes) ) {
            this.dependentes.add(dependentes);
            dependentes.setFamilias(this);
        }
    }

    public List<Dependentes> getDependentes() {
        return ObjectUtils.isEmpty(this.dependentes) ? null: Collections.unmodifiableList(this.dependentes);
    }

}
