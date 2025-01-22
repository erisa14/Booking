package com.booking.repositories;

import com.booking.models.BookingBackup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingBackupRepository extends CrudRepository<BookingBackup, Long> {

    List<BookingBackup> findAll();
}
