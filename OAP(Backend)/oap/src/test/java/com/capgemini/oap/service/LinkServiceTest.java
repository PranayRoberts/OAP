package com.capgemini.oap.service;

import com.capgemini.oap.exception.LinkException;
import com.capgemini.oap.model.Link;
import com.capgemini.oap.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LinkServiceTest {

    @InjectMocks
    private LinkService linkService = new LinkServiceImpl();
    @Mock
    private LinkRepository linkRepository;

    private final Link l1 = new Link("L1",8,"A","B",'A','B');
    private final Link l2 = new Link("L2", 8, "B","C", 'C','D');

    private final List<Link> list = new ArrayList<>();


    {
        l1.setLink_id(1);
        l2.setLink_id(2);
        list.addAll(List.of(l1, l2));
    }

    @Test
    void addLink(){
        when(linkRepository.save(l1)).thenReturn(l1);
        Link link = linkService.addLink(l1);
        assertThat(link.getName()).isEqualTo(l1.getName());

    }

    @Test
    void getAllLinks(){
        when(linkRepository.findAll()).thenReturn(list);
        List<Link> linkList = linkService.getAllLinks();
        assertThat(linkList.size()).isEqualTo(2);
    }

    @Test
    void getLinkById(){
        int id = 1;
        when(linkRepository.findById(id)).thenReturn(Optional.of(l1));
        Link link = linkService.getLinkById(id);
        assertThat(link.getLink_id().equals(id));
    }

    @Test
    void getLinkByIdWithException(){
        LinkException linkException=assertThrows(LinkException.class,()->linkService.getLinkById(1));
        assertEquals("Invalid Link ID",linkException.getMessage());
    }

    @Test
    void getLinkByName(){
        String name="L1";
        when(linkRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(l1));
        Link link = linkService.getLinkByName(name);
        assertThat(link.getName()).isEqualTo(name);
    }

    @Test
    void getLinkByNameWithException(){
        LinkException linkException=assertThrows(LinkException.class,()->linkService.getLinkByName("L1"));
        assertEquals("Invalid Link Name",linkException.getMessage());
    }

    @Test
    void deleteLink(){
        Link link=new Link();
        link.setLink_id(3);
        link.setName("L3");
        link.setLength(7);
        link.setFrom_node("C");
        link.setTo_node("D");
        Optional<Link> optionalLink=Optional.of(link);
        when(linkRepository.findById(3)).thenReturn(optionalLink);
        linkService.getLinkById(3);
        linkService.deleteLink(3);
        verify(linkRepository, times(1)).deleteById(3);

    }

    @Test
    void deleteLinkWithException(){
        LinkException linkException=assertThrows(LinkException.class,()->linkService.deleteLink(1));
        assertEquals("Invalid Link ID",linkException.getMessage());
    }

    @Test
    void updateLink(){
        int id = 2;
        when(linkRepository.findById(id)).thenReturn(Optional.of(l2));
        Link temp=l2;
        String name = "L2";
        temp.setName(name);
        Link link = linkService.updateLink(id,l2);
        assertThat(link.getName()).isEqualTo(name);
    }

    @Test
    void updateLinkWithException(){
        LinkException linkException=assertThrows(LinkException.class,()->linkService.updateLink(1,l1));
        assertEquals("Invalid Link ID",linkException.getMessage());
    }

    @Test
    void exceptionTest(){
        int id=15;
        when(linkRepository.findById(id)).thenThrow(LinkException.class);
        assertThrows(LinkException.class,()->linkService.getLinkById(id));
    }
}
