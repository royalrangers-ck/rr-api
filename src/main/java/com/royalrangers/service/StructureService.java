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

    public City createCity(CityDto cityDto) {
        Region region = regionRepository.findOne(cityDto.getRegionId());
        if (cityRepository.findByNameAndRegionId(cityDto.getName(), cityDto.getRegionId()) != null) {
            throw new NullPointerException();
        }
        City city = new City(region, cityDto.getName());
        return cityRepository.save(city);
    }

    public Section createSection(SectionDto sectionDto){
        Platoon platoon = platoonRepository.findOne(sectionDto.getPlatoonId());
        if (sectionRepository.findByName(sectionDto.getName()) != null) {
            throw new NullPointerException();
        }
        Section section = new Section(platoon,sectionDto.getName());
        return sectionRepository.save(section);
    }

    public Region createRegion(RegionDto regionDto) {
        Country country = countryRepository.findOne(regionDto.getCountryId());
        if (regionRepository.findByNameAndCountryId(regionDto.getName(), regionDto.getCountryId()) != null) {
            throw new NullPointerException();
        }
        Region region = new Region(country, regionDto.getName());
        return regionRepository.save(region);
    }

    public Country createCountry(CountryDto countryDto) {
        if (countryRepository.findByName(countryDto.getName()) != null) {
            throw new NullPointerException();
        }
        return countryRepository.save(new Country(countryDto.getName()));
    }

}
