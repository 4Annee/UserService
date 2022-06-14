package oes.userservice.Repositories;

import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, CompositeKeyGroup> {
    List<StudyGroup> findStudyGroupsByIdYear(int year);
    List<StudyGroup> findStudyGroupsByIdYearAndIdSection(int year,String section);
    StudyGroup findStudyGroupByIdYearAndIdSectionAndIdGroup(int year,String section,int group);
}