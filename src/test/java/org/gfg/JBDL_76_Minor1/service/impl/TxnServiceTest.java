package org.gfg.JBDL_76_Minor1.service.impl;

import org.gfg.JBDL_76_Minor1.dto.request.TxnRequest;
import org.gfg.JBDL_76_Minor1.exception.BookException;
import org.gfg.JBDL_76_Minor1.exception.UserException;
import org.gfg.JBDL_76_Minor1.model.Book;
import org.gfg.JBDL_76_Minor1.model.Txn;
import org.gfg.JBDL_76_Minor1.model.User;
import org.gfg.JBDL_76_Minor1.repository.TxnRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TxnServiceTest {

    @Mock
    private TxnRepository txnRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private TxnService txnService;

    @Test
    public void testCreateNullUser() throws BookException, UserException {
        TxnRequest txnRequest  = TxnRequest.builder().bookNo("bookNo").userEmail("email").build();
        when(userService.checkIfUserIsValid(any())).thenReturn(null);
        assertThrows(UserException.class , () -> txnService.create(txnRequest));
    }

    @Test
    public void testCreateNullBook() throws BookException, UserException {
        TxnRequest txnRequest  = TxnRequest.builder().bookNo("bookNo").userEmail("email").build();
        User user = User.builder().id(1).email("user@gmail.com").build();
        when(userService.checkIfUserIsValid(any())).thenReturn(user);

        when(bookService.checkIfBookIsValid(any())).thenReturn(null);
        assertThrows(BookException.class , () -> txnService.create(txnRequest));
    }

    @Test
    public void testCalculateSettlementAmount() throws ParseException {
        ReflectionTestUtils.setField(txnService, "validUpto", 10);
        ReflectionTestUtils.setField(txnService, "finePerDay", 1);
        Date issuedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-10-17 12:12:12");
        Txn txn = Txn.builder().txnId("1").issuedDate(issuedDate).build();
        Book book = Book.builder().securityAmount(100.0).id(1).build();
        assertEquals(79.0, txnService.calculateSettlementAmount(txn, book));

    }
}
