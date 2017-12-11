CREATE DATABASE IF NOT EXISTS `engagement_survey`;
USE `engagement_survey`;

CREATE TABLE IF NOT EXISTS `aspects` (
  `aspect_id` INT(11) NOT NULL AUTO_INCREMENT,
  `aspect` VARCHAR(255) DEFAULT NULL,
  `aspectdetails` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`aspect_id`),
  UNIQUE KEY `aspects_uniquekey` (`aspect`)
) ENGINE=INNODB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ;


CREATE TABLE IF NOT EXISTS `employee_feedback` (
  `survey_id` INT(11) NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME DEFAULT NULL,
  `empid` VARCHAR(11) DEFAULT NULL,
  `status` TINYINT(1) DEFAULT NULL,
  `submission_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`survey_id`)
) ENGINE=INNODB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `employee_feedback_stats` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `aspect_ranking` INT(11) DEFAULT NULL,
  `aspect_rating` INT(11) DEFAULT NULL,
  `aspect_id` INT(11) DEFAULT NULL,
  `survey_id` INT(11) DEFAULT NULL,
  `aspect_rating_reason` VARCHAR(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniquekey` (`survey_id`,`aspect_id`),
  KEY `FK9yca2maid940jwke3rmgwvl7p` (`aspect_id`),
   FOREIGN KEY (`aspect_id`) REFERENCES `aspects` (`aspect_id`),
  FOREIGN KEY (`survey_id`) REFERENCES `employee_feedback` (`survey_id`)
) ENGINE=INNODB AUTO_INCREMENT=981 DEFAULT CHARSET=utf8;



INSERT  INTO `aspects`(`aspect_id`,`aspect`,`aspectdetails`) VALUES
(1,'Salary','Are you satisfied with compensation, Bonus, Superannuation?'),
(2,'Safety',' Is working environment of  the company  is stable and safe ? Are you Allowed to express Opinions without any fear?'),
(3,'Personal-Growth','Are you acquiring new skills in your respective fields? Is your thought process getting enriched?'),
(4,'Advancement','Do you feel that you are making progress in your  career?'),
(5,'Achievement',' Do you  feel like you are achieving your goals ? Is the environment conductive to get you a sense of achievement?'),
(6,'Work','Are you happy with the kind of work you are allotted? Is the work challenging and interesting enough? Do you feel like your talent is getting utilized?'),
(7,'Responsibility','Do you get the feeling that you  are responsible of something?  Have I been given critical work which challenges my ability to deliver?  '),
(8,'Recognition','Do you feel that you are getting enough recognition?'),
(9,'Company-Policies',' Are you satisfied with policies of company on Leaves, Transport, Perks, Food ?'),
(10,'working-Conditions','Are you satisfied with condition of AC, Light, Quality of Machines, Desks, Chairs, Meeting Rooms, AV ? ');
