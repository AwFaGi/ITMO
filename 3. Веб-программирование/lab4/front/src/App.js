import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StartPage from "./components/pages/StartPage";
import LoginPage from "./components/pages/LoginPage";
import RegisterPage from "./components/pages/RegisterPage";
import MainPage from "./components/pages/MainPage";
import Header from "./components/common/Header";

function App() {
  return (
      <Router>
          <Header />
          <Routes>
              <Route path='/' element={<StartPage />}/>
              <Route path='/register' element={<RegisterPage />}/>
              <Route path='/login' element={<LoginPage />}/>
              <Route path='/main' element={<MainPage />}/>
          </Routes>
      </Router>
  );
}

export default App;
