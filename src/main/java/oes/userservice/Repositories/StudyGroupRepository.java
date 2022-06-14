package oes.userservice.Repositories;

import oes.userservice.Entities.CompositeKeys.CompositeKeyGroup;
import oes.userservice.Entities.StudyGroup;
import oes.userservice.Models.StudyGroup.SectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, CompositeKeyGroup> {
    List<StudyGroup> findStudyGroupsByIdYear(int year);
    List<StudyGroup> findStudyGroupsByIdYearAndIdSection(int year,String section);

    @Query("select s.id.year from StudyGroup s group by s.id.year")
    List<Integer> getYears();

    @Query("select s.id.section, s.id.group from StudyGroup s")
    List<SectionDTO> getYearSections(int year);
}