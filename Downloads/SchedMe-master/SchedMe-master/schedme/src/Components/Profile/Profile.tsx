import Navbar from "../SideNav/SideNav";
import TopNav from "../TopNav/TopNav";
import BackgroundPic from "./FullProfile/BackgroundPic/BackgroundPic";
import FullProfile from "./FullProfile/FullProfile";
import ProfilePic from "./FullProfile/ProfilePic/ProfilePic";
import ProfileSection from "./FullProfile/ProfileSection/ProfileSection";

export const Profile: React.FC<{}> = (props) => {
    return (
        <div className="container-fluid">            
            <div className="position-relative row">
                <TopNav/>
                <Navbar />
                <div className="col bg-warning p-0 d-flex align-items-center justify-content-center p-5" style={{ height: "100vh" }}>
                    <FullProfile/>
                </div>
            </div>
        </div>
    )
}
export default Profile;