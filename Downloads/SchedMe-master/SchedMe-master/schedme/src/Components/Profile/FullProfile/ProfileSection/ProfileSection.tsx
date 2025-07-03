export const ProfileSection: React.FC<{}> = (props) => {
    return (
        <div className="container-fluid bg-info border border-black p-0" style={{ height: "35%" }}>
            <div className="bg-success" style={{ height: "35%" }}>
                <div className="d-flex w-50 h-75 border border-black" style={{ fontSize: "50px" }}>First Last</div>
                <div className="d-flex w-50 border border-black">@username</div>
            </div>
            <div className="bg-info" style={{ height: "65%" }}>
                <div className="bg-warning h-50">
                    <div className="d-flex w-100 border border-black">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem explicabo velit aliquid, illum aliquam nesciunt tenetur in, nam amet magnam odit veritatis quis obcaecati consequuntur labore repellendus! Odit, inventore esse! Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus tenetur facere adipisci id voluptates ducimus mollitia quia error vero explicabo expedita ipsum voluptas, alias rerum voluptatibus, dignissimos illo dolor delectus?</p>
                    </div>

                    <div className="d-flex w-100 border border-black">
                        <div className="d-flex justify-content-start ms-5 border border-black" style={{ width: "calc(100% / 4)" }}>Some City, United States</div>
                        <div className="d-flex justify-content-start ms-5 border border-black" style={{ width: "calc(100% / 4)" }}>fakeWebsiteForShow.com</div>
                        <div className="d-flex justify-content-start ms-5 border border-black" style={{ width: "calc(100% / 4)" }}>Joined December 1, 1999</div>
                    </div>
                </div>
                <div className="bg-info h-50">
                    <div className="d-flex w-100 border border-black">
                        <div className="d-flex justify-content-start ms-5 border border-black" style={{ width: "calc(100% / 4)" }}>9,999 following</div>
                        <div className="d-flex justify-content-start ms-5 border border-black" style={{ width: "calc(100% / 4)" }}>9,999 followers</div>
                    </div>
                    <div className="d-flex border border-black h-50 w-100">
                        <div className="w-25 border border-black">Posts</div>
                        <div className="w-25 border border-black">Groups</div>
                        <div className="w-25 border border-black">Media</div>
                        <div className="w-25 border border-black">Likes</div>
                    </div>
                </div>
            </div>


        </div>
    )
}
export default ProfileSection;