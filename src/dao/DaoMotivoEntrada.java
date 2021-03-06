/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import daoInterfaces.MotivoEntradasInterface;
import entidades.Motivoentrada;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alexandre
 */
public class DaoMotivoEntrada implements MotivoEntradasInterface{

    @Override
    public void AdicionarMotivoEntrada(Motivoentrada motivo) {
        
        Session sessao= null;
        Transaction transacao = null;
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.save(motivo); 
            transacao.commit();
            System.out.println("Salvo com sucesso");  
        }
        catch(HibernateException e)
        {
            System.out.println("Erro ao iniciar a sessao para persistencia " + e);
            transacao.rollback();
        }
        finally
        {
            sessao.close(); 
        }      
    }

    @Override
    public void ApagarMotivoEntrada(Motivoentrada motivo) {
      
        Session sessao= null;
        Transaction transacao = null;
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(motivo); 
            transacao.commit();
            System.out.println("Salvo com sucesso");  
        }
        catch(HibernateException e)
        {
            System.out.println("Erro ao iniciar a sessao para persistencia " + e);
            transacao.rollback();
        }
        finally
        {
            sessao.close(); 
        }      
    }
    
     @Override
    public Motivoentrada BuscarMotivoEntradaId(int id) {
       
        Motivoentrada motivoEntrada = null;
        Session sessao = null; 
        Query query = null;
        Transaction transacao = null;
        
        try{
           sessao = HibernateUtil.getSessionFactory().openSession();
           transacao = sessao.beginTransaction();
           query = sessao.createQuery("from Motivoentrada where id = :id");
           query.setParameter("id",id);
           motivoEntrada = (Motivoentrada)query.uniqueResult();
           transacao.commit(); 
           
        }
        catch(HibernateException e)
        {
            System.out.println(e);
            transacao.rollback();
        }
        finally
        {
             sessao.close();
        }  
    return motivoEntrada;
    }
    
    @Override
    public List<Motivoentrada> BuscarMotivoEntradaLikeNome(String nome)
    {
        List<Motivoentrada> motivoEntrada = null;
        Session sessao = null; 
        Query query = null;
        Transaction transacao = null;
        
        try{
           sessao = HibernateUtil.getSessionFactory().openSession();
           transacao = sessao.beginTransaction();
           query = sessao.createQuery("from Motivoentrada m where m.nome LIKE :nome");
           query.setString("nome","%"+nome+"%");
           //query.setParameter("nome",nome);
           motivoEntrada = query.list();
           transacao.commit(); 
           
        }
        catch(HibernateException e)
        {
            System.out.println(e);
            transacao.rollback();
        }
        finally
        {
             sessao.close();
        }  
    return motivoEntrada;
    }
    
    @Override
    public void AlterarMotivoEntrada(Motivoentrada motivo) {
       
        Session sessao= null;
        Transaction transacao = null;
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.saveOrUpdate(motivo); 
            transacao.commit();
            System.out.println("Salvo com sucesso");  
        }
        catch(HibernateException e)
        {
            System.out.println("Erro ao iniciar a sessao para persistencia " + e);
            transacao.rollback();
        }
        finally
        {
            sessao.close(); 
        }      
    }
    
    @Override
    public List<Motivoentrada> BuscarMotivo(String nome) {
        
        Session sessao= null;
        Transaction transacao = null;
        List<Motivoentrada> lista = new ArrayList<>();
       
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            String HQL_QUERY="FROM MotivoEntrada m where  lower(m.nome) like lower(:nome) order by nome";
            Query query = sessao.createQuery(HQL_QUERY);
            query.setParameter("nome",'%'+nome+'%' );
            
            lista = query.list();
            transacao.commit(); 
       
        }
        catch(HibernateException e)
        {
            System.out.println(e);
            transacao.rollback();
        }
        finally
        {
             sessao.close();
        }  
    return lista;
    }

    @Override
    public List<Motivoentrada> BuscarTodosMotivos() {
        
        Session sessao= null;
        Transaction transacao = null;
        List<Motivoentrada> lista = new ArrayList<>();
       
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            String HQL_QUERY="FROM Motivoentrada order by nome";
            Query query = sessao.createQuery(HQL_QUERY);       
            lista = query.list();
            transacao.commit(); 
       
        }
        catch(HibernateException e)
        {
            System.out.println(e);
            transacao.rollback();
        }
        finally
        {
             sessao.close();
        }  
    return lista;
    }
    
}
