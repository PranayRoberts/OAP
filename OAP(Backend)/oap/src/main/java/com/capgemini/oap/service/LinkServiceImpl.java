package com.capgemini.oap.service;

import com.capgemini.oap.exception.LinkException;
import com.capgemini.oap.model.Link;
import com.capgemini.oap.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class LinkServiceImpl implements LinkService{

    Logger logger = Logger.getLogger(LinkServiceImpl.class.getName());
    public LinkServiceImpl(){}
    public LinkRepository linkRepository;

    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository){
        this.linkRepository = linkRepository;
    }

    @Override
    public Link addLink(Link link) {
        logger.info("Adding a new Link");
        linkRepository.save(link);
        return link;
    }

    @Override
    public List<Link> getAllLinks() {
        logger.info("Finding all Links");
        return linkRepository.findAll();
    }

    @Override
    public Link getLinkById(Integer id) throws LinkException {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isEmpty()) {
            logger.warning("Invalid Link ID");
            throw new LinkException("Invalid Link ID");
        }
        logger.info("Getting Link By ID");
        return link.get();
    }

    @Override
    public Link getLinkByName(String name) {
        Optional<Link> link= linkRepository.findByNameIgnoreCase(name);
        if(link.isEmpty()) {
            logger.warning("Invalid Link Name");
            throw new LinkException("Invalid Link Name");
        }
        logger.info("Getting Link By Name");
        return link.get();
    }

    @Override
    public void deleteLink(Integer id) {

        Optional<Link> optionalLink = linkRepository.findById(id);
        if(optionalLink.isEmpty()) {
            logger.warning("Invalid Link ID");
            throw new LinkException("Invalid Link ID");
        }
        logger.info("Deleting Link By ID");
        linkRepository.deleteById(id);
    }

    @Override
    public Link updateLink(Integer id, Link link) {
        Optional<Link> link1 =linkRepository.findById(id);
        if (link1.isEmpty()) {
            logger.warning("Invalid Link ID");
            throw new LinkException("Invalid Link ID");
        }
        logger.info("Updating Link Details");
        link1.get().setName(link.getName());
        link1.get().setLength(link.getLength());
        link1.get().setFrom_node(link.getFrom_node());
        link1.get().setTo_node(link.getTo_node());
        return link1.get();
    }
}
