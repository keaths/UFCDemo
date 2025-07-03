import React from 'react';
import logo from './logo.svg';
import './App.css';
import { HomePage } from './Components/HomePage/HomePage';
import SpecificDay from './Components/SpecificDay/SpecificDay';
import Feed from './Components/Feed/Feed';
import Profile from './Components/Profile/Profile';
import MyGroups from './Components/MyGroups/MyGroups';
import UpcomingEvents from './Components/UpcomingEvents/UpcomingEvents';

function App() {
  return (
    <div className="App">
      {/* <Profile/> */}
      {/* <MyGroups/> */}
      <UpcomingEvents/>
      {/* <Feed/> */}
      {/* <SpecificDay/> */}
    </div>
  );
}

export default App;
