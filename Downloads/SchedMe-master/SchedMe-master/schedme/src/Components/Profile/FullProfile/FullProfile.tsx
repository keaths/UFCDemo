import BackgroundPic from "./BackgroundPic/BackgroundPic";
import ProfilePic from "./ProfilePic/ProfilePic";
import ProfileSection from "./ProfileSection/ProfileSection";

export const FullProfile: React.FC<{}> = (props) => {
    return (
        <div className="container-fluid h-100 bg-success p-0" style={{ width: "100%" }}>
            <div className="position-relative" style={{ height: "65%" }}>
                <BackgroundPic />
                <ProfilePic />
            </div>
            <ProfileSection />
        </div>
    )
}

export default FullProfile;