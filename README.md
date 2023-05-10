<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".




<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin1-url]
[![LinkedIn][linkedin-shield]][linkedin2-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/noah-hw-kim/better-buy">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Better-Buy-App</h3>

  <p align="center">
    Use Better Buy to compare products and determine the best value product!
    <br />
    <a href="https://github.com/noah-hw-kim/better-buy"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/noah-hw-kim/better-buy">View Demo</a>
    ·
    <a href="https://github.com/noah-hw-kim/better-buy/issues">Report Bug</a>
    ·
    <a href="https://github.com/noah-hw-kim/better-buy/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->

<summary>Table of Contents</summary>
<ol>
  <li>
    <a href="#about-the-project">About The Project</a>
    <ul>
      <li><a href="#built-with">Built With</a></li>
    </ul>
  </li>
  <li>
    <a href="#getting-started">Getting Started</a>
    <ul>
      <li><a href="#prerequisites">Prerequisites</a></li>
      <li><a href="#installation">Installation</a></li>
    </ul>
  </li>
  <li><a href="#usage">Usage</a></li>
  <li><a href="#roadmap">Roadmap</a></li>
  <li><a href="#contributing">Contributing</a></li>
  <li><a href="#license">License</a></li>
  <li><a href="#contact">Contact</a></li>
  <li><a href="#acknowledgments">Acknowledgments</a></li>
</ol>




<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)
Project details

Here's a blank template to get started: To avoid retyping too much info. Do a search and replace with your text editor for the following: `noah-hw-kim`, `better-buy`, `twitter_handle`, `linkedin_username`, `email_client`, `email`, `project_title`, `project_description`

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With
FrontEnd
<ul>
  <li><a href="#Javascript-url">JavaScript</a></li>
  <li><a href="#built-with">Bootstrap</a></li>
  <li><a href="#built-with">HTML</a></li>
  <li><a href="#built-with">CSS</a></li>
</ul>

BackEnd
<ul>
  <li><a href="#built-with">Java</a></li>
  <li><a href="#built-with">Spring Boot</a></li>
  <li><a href="#built-with">MongoDB</a></li>
  <li><a href="#built-with">Maven Project</a></li>
</ul>

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started
### Prerequisites
1. <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java 17 version</a> (We're testing the above version) 
* Check java version (Windows/Mac/Linux OS)
  ```sh
  java -version
  ```
  
2. <a href="https://www.mongodb.com/docs/atlas/getting-started/">MongoDB</a>
3. This app runs with spring boot. We tested our app running with two major IDE VScode and Intellij.
* For VS code, it requires extensions "Extension Pack for Java" and "Spring Boot Extension Pack". <br>
Installation instruction link [https://code.visualstudio.com/docs/java/java-spring-boot](https://code.visualstudio.com/docs/java/java-spring-boot)
* For Intellij, no extensions required.

### Installation
1. Clone the repo
```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```

2. Create .env file in "resources" directory (Backend -> src -> main -> java -> com.orrijoa.BetterBuy -> resources)
3. Define .env file accordingly <br>
e.x.) MONGO_DATABASE="[Database_Name]" <br>
MONGO_USER="[User_Name]" <br>
MONGO_PASSWORD="[User_Password]" <br>
MONGO_CLUSTER="[Cluster_Name]"

<!-- 
1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/noah-hw-kim/better-buy.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `config.js`
   ```js
   const API_KEY = 'ENTER YOUR API';
   ``` -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
1. Run "BetterBuyApplication.java" in Backend directory (Backend -> src -> main -> java\com\orrijoa\BetterBuy)
* Intellij

* VS code

2. Run "main.html" in Frontend directory


3. Main Page
[![Main Page][mainPage-screenshot]](https://github.com/noah-hw-kim/better-buy/blob/main/Image/BetterBuy_MainPage.png)


Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Available search box to search with non-exact match
- [ ] Available user to select the unit system in between imperial or metric and update the base unit accordingly 
- [ ] Change the base unit according to the categories
    - e.x.) current base unit for mass = oz -> Meat, Fish and Seafood base unit for mass = lb
- [ ] Launch App version  

See the [open issues](https://github.com/noah-hw-kim/better-buy/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the APACHE-2.0 License. See `LICENSE.md` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Noah(Hyeonwoo) Kim - [@twitter_handle](https://twitter.com/twitter_handle) - noah.hw.kim@gmail.com
Lanny Ngo - lannyngo@gmail.com

Project Link: [https://github.com/noah-hw-kim/better-buy](https://github.com/noah-hw-kim/better-buy)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* QUDT Libraries - [https://github.com/qudt/qudt-public-repo](https://github.com/qudt/qudt-public-repo)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/noah-hw-kim/better-buy.svg?style=for-the-badge
[contributors-url]: https://github.com/noah-hw-kim/better-buy/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/noah-hw-kim/better-buy.svg?style=for-the-badge
[forks-url]: https://github.com/noah-hw-kim/better-buy/network/members
[stars-shield]: https://img.shields.io/github/stars/noah-hw-kim/better-buy.svg?style=for-the-badge
[stars-url]: https://github.com/noah-hw-kim/better-buy/stargazers
[issues-shield]: https://img.shields.io/github/issues/noah-hw-kim/better-buy.svg?style=for-the-badge
[issues-url]: https://github.com/noah-hw-kim/better-buy/issues
[license-shield]: https://img.shields.io/github/license/noah-hw-kim/better-buy.svg?style=for-the-badge
[license-url]: https://github.com/noah-hw-kim/better-buy/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin1-url]: https://linkedin.com/in/noah-hw-kim/
[linkedin2-url]: https://linkedin.com/in/ngolanny/
[product-screenshot]: images/screenshot.png
[Javascript-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript
[Bootstrap-url]: https://getbootstrap.com
[HTML-url]: https://developer.mozilla.org/en-US/docs/Web/HTML
[CSS-url]: https://developer.mozilla.org/en-US/docs/Web/CSS
[Java-url]: https://www.java.com/en/
[SpringBoot-url]: https://spring.io/
[MongoDB-url]: https://www.mongodb.com/
[Maven-url]: https://maven.apache.org/

