package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.structure.*;
import com.royalrangers.enums.ImageType;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class StructureService {

    @Autowired
    private DropboxService dropboxService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public Platoon getPlatoonData() {
        return platoonRepository.findOne(userService.getAuthenticatedUser().getPlatoon().getId());
    }

    public void createPlatoon(PlatoonDto platoonDto) {
        Platoon platoon = new Platoon();
        platoon.setCreateDate(platoon.getCreateDate());
        platoon.setUpdateDate(platoon.getUpdateDate());
        platoon.setName(platoonDto.getName());
        platoon.setHistory(platoonDto.getHistory());
        platoon.setAddress(platoonDto.getAddress());
        platoon.setGroup(groupRepository.findOne(platoonDto.getGroupId()));
        platoon.setCity(groupRepository.findOne(platoonDto.getGroupId()).getCity().getName());
        platoon.setMeetTime(platoonDto.getMeetTime());
        platoonRepository.save(platoon);
    }

    public void updatePlatoon(Long id, PlatoonDto update) {
        Platoon platoon = platoonRepository.findOne(id);
        platoon.setCreateDate(update.getCreateDate());
        platoon.setUpdateDate(new Date());
        platoon.setName(update.getName());
        platoon.setHistory(update.getHistory());
        platoon.setAddress(update.getAddress());
        platoon.setGroup(groupRepository.findOne(update.getGroupId()));
        platoon.setCity(groupRepository.findOne(update.getGroupId()).getCity().getName());
        platoon.setMeetTime(update.getMeetTime());
        platoonRepository.save(platoon);
    }

    public void setPlatoonLogoUrl(Long id, String logoUrl) throws DbxException {
        Platoon platoon = platoonRepository.findOne(id);

        if (platoon.getLogoUrl() != null) {
            dropboxService.deleteImage(platoon.getLogoUrl(), ImageType.PLATOON_LOGO);
        }

        platoon.setLogoUrl(logoUrl);
        platoonRepository.save(platoon);
    }

    public void delPlatoonLogoUrl(Long id) throws DbxException {
        Platoon platoon = platoonRepository.findOne(id);

        if (platoon.getLogoUrl() != null) {
            dropboxService.deleteImage(platoon.getLogoUrl(), ImageType.PLATOON_LOGO);
        }

        platoon.setLogoUrl(null);
        platoonRepository.save(platoon);
    }

    public boolean createGroup(GroupDto groupDto){
        City city = cityRepository.findOne(groupDto.getCityId());
        Group group = new Group(city,groupDto.getName());
        Set<Group> groupsSet = city.getGroups();
        group.setCreateDate(groupDto.getCreateDate());
        group.setUpdateDate(groupDto.getUpdateDate());
        group.setHistory(groupDto.getHistory());
        group.setAddress(groupDto.getAddress());
        if (!groupsSet.add(group))
            return false;
        city.setGroups(groupsSet);
        cityRepository.save(city);
        return true;
    }

    public boolean createSection(SectionDto sectionDto){
        Platoon platoon = platoonRepository.findOne(sectionDto.getPlatoonId());
        Section section = new Section(platoon,sectionDto.getName());
        Set<Section> sectionSet = platoon.getSections();
        section.setCreateDate(sectionDto.getCreateDate());
        section.setUpdateDate(sectionDto.getUpdateDate());
        if (!sectionSet.add(section))
            return false;
        platoon.setSections(sectionSet);
        platoonRepository.save(platoon);
        return  true;
    }

    public boolean createCity(CityDto cityDto){
        Country country = countryRepository.findOne(cityDto.getCountryId());
        if(cityRepository.findByNameAndCountryId(cityDto.getName(), cityDto.getCountryId()) != null)
            return false;
        City city = new City(country, cityDto.getName());
        Set<City> citySet = country.getCity();
        city.setCreateDate(cityDto.getCreateDate());
        city.setUpdateDate(cityDto.getUpdateDate());
        if (!citySet.add(city))
            return false;
        country.setCity(citySet);
        countryRepository.save(country);
        return true;
    }

    public boolean createCountry(CountryDto countryDto){
        if (countryRepository.findByName(countryDto.getName()) != null)
            return false;
        countryRepository.save(new Country(countryDto.getName()));
        return true;
    }
}
