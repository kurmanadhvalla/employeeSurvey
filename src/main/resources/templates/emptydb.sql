CREATE DATABASE IF NOT EXISTS `engagement_survey`;


CREATE TABLE `aspects` (
  `aspect_id` int(11) NOT NULL AUTO_INCREMENT,
  `aspect` varchar(255) DEFAULT NULL,
  `aspectdetails` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aspect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8


CREATE TABLE `employee_feedback` (
  `survey_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `empid` varchar(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `submission_date` datetime DEFAULT NULL,
  PRIMARY KEY (`survey_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8


CREATE TABLE `employee_feedback_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aspect_ranking` int(11) DEFAULT NULL,
  `aspect_rating` int(11) DEFAULT NULL,
  `aspect_id` int(11) DEFAULT NULL,
  `survey_id` int(11) DEFAULT NULL,
  `aspect_rating_reason` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniquekey` (`survey_id`,`aspect_id`),
  KEY `FK9yca2maid940jwke3rmgwvl7p` (`aspect_id`),
   FOREIGN KEY (`aspect_id`) REFERENCES `aspects` (`aspect_id`),
  FOREIGN KEY (`survey_id`) REFERENCES `employee_feedback` (`survey_id`)
) ENGINE=InnoDB AUTO_INCREMENT=981 DEFAULT CHARSET=utf8


INSERT INTO aspects(aspect) VALUES
('salary','Are you satisfied with compensation, Bonus, Superannuation?'),
('safety',' Is working environment of  the company  is stable and safe ? Are you Allowed to express Opinions without any fear?'),
('personal_growth','Are you acquiring new skills in your respective fields? Is your thought process getting enriched?'),
('advancement','Do you feel that you are making progress in your  career?'),
('achievement',' Do you  feel like you are achieving your goals ? Is the environment conductive to get you a sense of achievement?'),
('work','Are you happy with the kind of work you are allotted? Is the work challenging and interesting enough? Do you feel like your talent is getting utilized?'),
('responsibility','Do you get the feeling that you  are responsible of something?  Have I been given critical work which challenges my ability to deliver?  '),
('recognition','Do you feel that you are getting enough recognition?'),
('company_policies',' Are you satisfied with policies of company on Leaves, Transport, Perks, Food ?'),
('working_conditions'.'Are you satisfied with condition of AC, Light, Quality of Machines, Desks, Chairs, Meeting Rooms, AV ? ');