package ma.zs.budgetInstitut.service.impl.admin.expressionbesoin;


import ma.zs.budgetInstitut.bean.core.expressionbesoin.ExpressionBesoinDetail;
import ma.zs.budgetInstitut.dao.criteria.core.expressionbesoin.ExpressionBesoinDetailCriteria;
import ma.zs.budgetInstitut.dao.facade.core.expressionbesoin.ExpressionBesoinDetailDao;
import ma.zs.budgetInstitut.dao.specification.core.expressionbesoin.ExpressionBesoinDetailSpecification;
import ma.zs.budgetInstitut.service.facade.admin.expressionbesoin.ExpressionBesoinDetailAdminService;
import ma.zs.budgetInstitut.zynerator.service.AbstractServiceImpl;
import ma.zs.budgetInstitut.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;

import ma.zs.budgetInstitut.service.facade.admin.expressionbesoin.ExpressionBesoinAdminService ;
import ma.zs.budgetInstitut.bean.core.expressionbesoin.ExpressionBesoin ;
import ma.zs.budgetInstitut.service.facade.admin.produit.ProduitAdminService ;
import ma.zs.budgetInstitut.bean.core.produit.Produit ;

import java.util.List;
@Service
public class ExpressionBesoinDetailAdminServiceImpl extends AbstractServiceImpl<ExpressionBesoinDetail, ExpressionBesoinDetailCriteria, ExpressionBesoinDetailDao> implements ExpressionBesoinDetailAdminService {






    public void findOrSaveAssociatedObject(ExpressionBesoinDetail t){
        if( t != null) {
            t.setExpressionBesoin(expressionBesoinService.findOrSave(t.getExpressionBesoin()));
            t.setProduit(produitService.findOrSave(t.getProduit()));
        }
    }

    public List<ExpressionBesoinDetail> findByExpressionBesoinId(Long id){
        return dao.findByExpressionBesoinId(id);
    }
    public int deleteByExpressionBesoinId(Long id){
        return dao.deleteByExpressionBesoinId(id);
    }
    public long countByExpressionBesoinId(Long id){
        return dao.countByExpressionBesoinId(id);
    }
    public List<ExpressionBesoinDetail> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitId(Long id){
        return dao.countByProduitId(id);
    }

    public void accorderQuantitesChef(Long idExpressionBesoinDetail, BigDecimal qteDemandee, BigDecimal qteAccordee, BigDecimal qteLivre) {
        ExpressionBesoinDetail expressionBesoinDetail = dao.findById(idExpressionBesoinDetail).orElse(null);

        if (expressionBesoinDetail != null) {
            // Accord du chef d'établissement sur les quantités
            expressionBesoinDetail.setQteDemandee(qteDemandee);
            expressionBesoinDetail.setQteAccordee(qteAccordee);
            expressionBesoinDetail.setQteLivre(qteLivre);

            // Enregistrement des modifications
            dao.save(expressionBesoinDetail);
        }
    }




    public void configure() {
        super.configure(ExpressionBesoinDetail.class, ExpressionBesoinDetailSpecification.class);
    }


    @Autowired
    private ExpressionBesoinAdminService expressionBesoinService ;
    @Autowired
    private ProduitAdminService produitService ;

    public ExpressionBesoinDetailAdminServiceImpl(ExpressionBesoinDetailDao dao) {
        super(dao);
    }

}
