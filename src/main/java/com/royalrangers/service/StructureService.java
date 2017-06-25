package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.structure.*;
import com.royalrangers.enums.ImageType;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
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
    private RegionRepository regionRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserService userService;

    public Platoon getPlatoonData() {
        return platoonRepository.findOne(userService.getAuthenticatedUser().getPlatoon().getId());
    }

    public Platoon createPlatoon(PlatoonDto platoonDto) {
        Platoon platoon = new Platoon();
        platoon.setCreateDate(platoon.getCreateDate());
        platoon.setUpdateDate(platoon.getUpdateDate());
        platoon.setName(platoonDto.getName());
        platoon.setHistory(platoonDto.getHistory());
        platoon.setAddress(platoonDto.getAddress());
        platoon.setCity(cityRepository.findOne(platoonDto.getCityId()));
        platoon.setMeetTime(platoonDto.getMeetTime());
        return platoonRepository.save(platoon);
    }

    public Platoon updatePlatoon(Long id, PlatoonDto update) {
        Platoon platoon = platoonRepository.findOne(id);
        platoon.setUpdateDate(new Date());
        platoon.setName(update.getName());
        platoon.setHistory(update.getHistory());
        platoon.setAddress(update.getAddress());
        platoon.setCity(cityRepository.findOne(update.getCityId()));
        platoon.setMeetTime(update.getMeetTime());
        return platoonRepository.save(platoon);
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

    public boolean createCity(CityDto cityDto){
        Region region = regionRepository.findOne(cityDto.getRegionId());
        City city = new City(region, cityDto.getName());
        Set<City> citySet = region.getCities();
        if (!citySet.add(city))
            return false;
        region.setCities(citySet);
        regionRepository.save(region);
        return true;
    }

    public boolean createSection(SectionDto sectionDto){
        Platoon platoon = platoonRepository.findOne(sectionDto.getPlatoonId());
        Section section = new Section(platoon,sectionDto.getName());
        Set<Section> sectionSet = platoon.getSections();
        if (!sectionSet.add(section))
            return false;
        platoon.setSections(sectionSet);
        platoonRepository.save(platoon);
        return  true;
    }

    public boolean createRegion(RegionDto regionDto){
        Country country = countryRepository.findOne(regionDto.getCountryId());
        if(regionRepository.findByNameAndCountryId(regionDto.getName(), regionDto.getCountryId()) != null)
            return false;
        Region region = new Region(country, regionDto.getName());
        Set<Region> regionSet = country.getRegions();
        if (!regionSet.add(region))
            return false;
        country.setRegions(regionSet);
        countryRepository.save(country);
        return true;
    }
    public boolean createCountry(CountryDto countryDto){
        if (countryRepository.findByName(countryDto.getName()) != null)
            return false;
        countryRepository.save(new Country(countryDto.getName()));
        return true;
    }

    public Region getRegion(RegionDto regionDto){
        return regionRepository.findByNameAndCountryId(regionDto.getName(), regionDto.getCountryId());
    }

    public City getCity(CityDto cityDto){
        return cityRepository.findByNameAndRegionId(cityDto.getName(),cityDto.getRegionId());
    }

    public Section getSection(SectionDto sectionDto){
        return sectionRepository.findByNameAndPlatoonId(sectionDto.getName(), sectionDto.getPlatoonId());
    }

    public Country getCountry (CountryDto countryDto){
        return countryRepository.findByName(countryDto.getName());
    }

}
